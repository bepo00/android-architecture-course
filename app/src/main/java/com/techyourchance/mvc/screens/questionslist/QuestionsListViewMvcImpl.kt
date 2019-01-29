package com.techyourchance.mvc.screens.questionslist

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc
import com.techyourchance.mvc.screens.common.BaseViewMvc

class QuestionsListViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) : BaseObservableViewMvc<QuestionsListViewMvc.Listener>(),QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {
    private val mRecyclerView: RecyclerView
    private val mRecyclerViewAdapter: QuestionsListAdapter

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list, parent, false))
        mRecyclerView = findViewById(R.id.lst_questions)
        mRecyclerView.layoutManager = LinearLayoutManager(getContext())
        mRecyclerViewAdapter = QuestionsListAdapter(LayoutInflater.from(getContext()), this)
        mRecyclerView.adapter = mRecyclerViewAdapter
    }

    override fun bindQuestions(questions: ArrayList<Question>) {
        mRecyclerViewAdapter.bindQuestions(questions)
    }

    override fun onQuestionClicked(question: Question) {
        mListeners.forEach { it.onQuestionClicked(question) }
    }
}