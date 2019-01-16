package com.techyourchance.mvc.screens.questionslist

import android.view.View
import com.techyourchance.mvc.questions.Question

interface QuestionsListViewMvc {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun getRootView(): View
    fun bindQuestions(questions: ArrayList<Question>)
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
}