package com.techyourchance.mvc.screens.questionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc
import com.techyourchance.mvc.screens.common.BaseViewMvc

class QuestionsListItemViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) : BaseObservableViewMvc<QuestionsListItemViewMvc.Listener>(), QuestionsListItemViewMvc {
    private val txtTitle: TextView
    private lateinit var mQuestion: Question
    init {
        setRootView(inflater.inflate(R.layout.layout_question_list_item, parent, false))
        getRootView().setOnClickListener { mListeners.forEach { listener -> listener.onQuestionClicked(mQuestion)} }
        txtTitle = findViewById(R.id.txt_title)
    }

    override fun bindQuestion(question: Question) {
        mQuestion = question
        txtTitle.text = question.title
    }
}