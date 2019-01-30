package com.techyourchance.mvc.screens.questiondetails

import android.os.Bundle
import com.techyourchance.mvc.R
import android.content.Intent
import android.content.Context
import android.widget.Toast
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema
import com.techyourchance.mvc.networking.QuestionSchema
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionDetailsActivity : BaseActivity(), FetchQuestionDetailsUseCase.Listener {
    private lateinit var mQuestionId: String
    private lateinit var mViewMvc: QuestionDetailsViewMvc
    private lateinit var mFetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvc = mCompositionRoot.getViewMcvFactory().getQuestionDetailsViewMvc(null)
        mFetchQuestionDetailsUseCase = mCompositionRoot.getFetchQuestionDetailsUseCase()
        mQuestionId = intent.getStringExtra(EXTRA_QUESTION_ID)

        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        mFetchQuestionDetailsUseCase.registerListener(this)
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(mQuestionId)
    }

    override fun onStop() {
        super.onStop()
        mFetchQuestionDetailsUseCase.unregisterListener(this)
    }

    private fun fetchQuestionDetails() {

    }

    fun bindQuestionDetails(questionDetails: QuestionDetails) {
        mViewMvc.bindQuestionDetails(questionDetails)
    }

    private fun networkCallFailed(){
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    //FetchDetailsUseCase Listener
    override fun onQuestionDetailsFetched(questionDetails: QuestionDetails) {
        bindQuestionDetails(questionDetails)
    }

    override fun onQuestionDetailsFetchFailure() {
        networkCallFailed()
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
