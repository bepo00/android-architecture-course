package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question

class QuestionsListAdapter(private val inflater: LayoutInflater,
                           private val mOnQuestionClickListener: OnQuestionClickListener) : RecyclerView.Adapter<QuestionsListAdapter.ViewHolder>(), QuestionsListItemViewMvc.Listener {

    data class ViewHolder(val viewMvc: QuestionsListItemViewMvc) : RecyclerView.ViewHolder(viewMvc.getRootView())

    private var mQuestions: List<Question> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val viewMvc = QuestionsListItemViewMvcImpl(inflater, viewGroup)
        viewMvc.registerListener(this)
        return ViewHolder(viewMvc)
    }

    fun bindQuestions(questions: List<Question>) {
        mQuestions = questions.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mQuestions.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        viewHolder.viewMvc.bindQuestion(mQuestions[pos])
    }

    override fun onQuestionClicked(question: Question) {
        mOnQuestionClickListener.onQuestionClicked(question)
    }

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }
}
