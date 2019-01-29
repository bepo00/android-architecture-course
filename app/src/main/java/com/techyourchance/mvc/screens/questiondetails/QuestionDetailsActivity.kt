package com.techyourchance.mvc.screens.questiondetails

import android.os.Bundle
import com.techyourchance.mvc.R
import android.content.Intent
import android.content.Context
import android.widget.Toast
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionDetailsActivity : BaseActivity() {

    private lateinit var mQuestionId: String
    private lateinit var mStackoverflowApi: StackoverflowApi
    private lateinit var mViewMvc: QuestionDetailsViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvc = mCompositionRoot.getViewMcvFactory().getQuestionDetailsViewMvc(null)
        mStackoverflowApi = mCompositionRoot.getStackOverflowApi()
        mQuestionId = intent.getStringExtra(EXTRA_QUESTION_ID)

        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        fetchQuestionDetails()
    }

    private fun fetchQuestionDetails() {
        mStackoverflowApi.fetchQuestionDetails(mQuestionId)
                .enqueue(object : Callback<QuestionDetailsResponseSchema> {
                    override fun onFailure(call: Call<QuestionDetailsResponseSchema>, t: Throwable) {
                        networkCallFailed()
                    }

                    override fun onResponse(call: Call<QuestionDetailsResponseSchema>, response: Response<QuestionDetailsResponseSchema>) {
                        if(response.isSuccessful){
                            val questionSchema = response.body()!!.getQuestion()
                            val questionDetails = QuestionDetails(questionSchema.id, questionSchema.title, questionSchema.body)
                            mViewMvc.bindQuestionDetails(questionDetails)
                        } else{
                            networkCallFailed()
                        }
                    }

                })
    }

    private fun networkCallFailed(){
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }
}
