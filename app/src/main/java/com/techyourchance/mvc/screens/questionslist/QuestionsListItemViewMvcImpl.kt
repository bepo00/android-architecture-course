package com.techyourchance.mvc.screens.questionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseViewMvc

class QuestionsListItemViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup) : BaseViewMvc(), QuestionsListItemViewMvc {
    private val txtTitle = findViewById<TextView>(R.id.txt_title)
    private val listeners = arrayListOf<QuestionsListItemViewMvc.Listener>()
    private lateinit var mQuestion: Question
    init {
        setRootView(inflater.inflate(R.layout.layout_question_list_item, parent, false))
        getRootView().setOnClickListener { listeners.forEach { listener -> listener.onQuestionClicked(mQuestion)} }
    }

    override fun bindQuestion(question: Question) {
        mQuestion = question
        txtTitle.text = question.title
    }

    override fun registerListener(listener: QuestionsListItemViewMvc.Listener) {
        listeners.add(listener) //To change body of created functions use File | Settings | File Templates.
    }

    override fun unregisterListener(listener: QuestionsListItemViewMvc.Listener) {
        listeners.remove(listener)
    }
}