package com.techyourchance.mvc.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast

import com.techyourchance.mvc.R
import com.techyourchance.mvc.common.Constants
import com.techyourchance.mvc.networking.QuestionSchema
import com.techyourchance.mvc.networking.QuestionsListResponseSchema
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseActivity
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener {

    private lateinit var mStackoverflowApi: StackoverflowApi

    private lateinit var mViewMvc: QuestionsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewMvc = mCompositionRoot.getViewMcvFactory().getQuestionsListViewMvc(null)
        mViewMvc.registerListener(this)

        mStackoverflowApi = mCompositionRoot.getStackOverflowApi()

        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        fetchQuestions()
    }

    private fun fetchQuestions() {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(object : Callback<QuestionsListResponseSchema> {
                    override fun onResponse(call: Call<QuestionsListResponseSchema>, response: Response<QuestionsListResponseSchema>) {
                        if (response.isSuccessful) {
                            bindQuestions(response.body()!!.questions)
                        } else {
                            networkCallFailed()
                        }
                    }

                    override fun onFailure(call: Call<QuestionsListResponseSchema>, t: Throwable) {
                        networkCallFailed()
                    }
                })
    }

    private fun bindQuestions(questionSchemas: List<QuestionSchema>) {
        val questions = ArrayList<Question>(questionSchemas.size)
        for (questionSchema in questionSchemas) {
            questions.add(Question(questionSchema.id, questionSchema.title))
        }
       mViewMvc.bindQuestions(questions)
    }

    private fun networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onQuestionClicked(question: Question) {
        QuestionDetailsActivity.start(this@QuestionsListActivity, question.id)
    }
}
