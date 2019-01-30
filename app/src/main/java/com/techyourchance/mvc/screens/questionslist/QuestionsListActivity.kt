package com.techyourchance.mvc.screens.questionslist

import android.os.Bundle
import android.widget.Toast
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseActivity
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity
import java.util.*

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener {

    private lateinit var mFetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase
    private lateinit var mViewMvc: QuestionsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewMvc = mCompositionRoot.getViewMcvFactory().getQuestionsListViewMvc(null)
        mFetchLastActiveQuestionsUseCase = mCompositionRoot.getFetchLastActiveQuestionsUseCase()

        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        mFetchLastActiveQuestionsUseCase.registerListener(this)
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
        mFetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    private fun bindQuestions(questions: ArrayList<Question>) {
       mViewMvc.bindQuestions(questions)
    }

    private fun networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    //QuestionListViewMvc Listener
    override fun onQuestionClicked(question: Question) {
        QuestionDetailsActivity.start(this@QuestionsListActivity, question.id)
    }

    //FetchLastActiveQuestionUseCase Listener
    override fun onLastActiveQuestionsFetched(questions: ArrayList<Question>) {
        bindQuestions(questions)
    }

    override fun onLastActiveQuestionsFetchFailed() {
        networkCallFailed()
    }
}
