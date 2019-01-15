package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question

class QuestionsListViewMvc : QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvcImpl {
    private val mRootView: View
    private var mLstQuestions: ListView? = null
    private var mQuestionsListAdapter: QuestionsListAdapter? = null
    private val mListeners = arrayListOf<QuestionsListViewMvcImpl.Listener>()

    constructor(layoutInflater: LayoutInflater, parent: ViewGroup?) {
        mRootView = layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
        mLstQuestions = findViewById(R.id.lst_questions)
        mQuestionsListAdapter = QuestionsListAdapter(getContext(), this)
        mLstQuestions!!.adapter = mQuestionsListAdapter
    }

    private fun <T : View> findViewById(id: Int): T {
        return getRootView().findViewById(id)
    }

    private fun getContext(): Context {
        return mRootView.context
    }

    override fun getRootView(): View {
        return mRootView
    }

    override fun bindQuestions(questions: ArrayList<Question>) {
        mQuestionsListAdapter!!.clear()
        mQuestionsListAdapter!!.addAll(questions)
        mQuestionsListAdapter!!.notifyDataSetChanged()
    }

    override fun registerListener(listener: QuestionsListViewMvcImpl.Listener) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListViewMvcImpl.Listener) {
        mListeners.remove(listener)
    }

    override fun onQuestionClicked(question: Question) {
        mListeners.forEach { it.onQuestionClicked(question) }
    }
}