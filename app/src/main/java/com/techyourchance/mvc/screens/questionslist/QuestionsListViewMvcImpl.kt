package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question

class QuestionsListViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) : QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {
    private val mRootView: View = layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
    private val mRecyclerView: RecyclerView
    private val mRecyclerViewAdapter: QuestionsListAdapter
    private val mListeners = arrayListOf<QuestionsListViewMvc.Listener>()

    init {
        mRecyclerView = findViewById(R.id.lst_questions)
        mRecyclerView.layoutManager = LinearLayoutManager(getContext())
        mRecyclerViewAdapter = QuestionsListAdapter(LayoutInflater.from(getContext()), this)
        mRecyclerView.adapter = mRecyclerViewAdapter
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
        mRecyclerViewAdapter.bindQuestions(questions)
    }

    override fun registerListener(listener: QuestionsListViewMvc.Listener) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListViewMvc.Listener) {
        mListeners.remove(listener)
    }

    override fun onQuestionClicked(question: Question) {
        mListeners.forEach { it.onQuestionClicked(question) }
    }
}