package com.techyourchance.mvc.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.mvc.screens.questionslist.QuestionsListItemViewMvc
import com.techyourchance.mvc.screens.questionslist.QuestionsListItemViewMvcImpl
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvc
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvcImpl

class ViewMvcFactory(layoutInflater: LayoutInflater) {
    private val mLayoutInflater = layoutInflater

    fun getQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvcImpl(mLayoutInflater, parent, this)
    }

    fun getQuestionsListItemViewMvc(parent: ViewGroup?): QuestionsListItemViewMvc {
        return QuestionsListItemViewMvcImpl(mLayoutInflater, parent)
    }
}