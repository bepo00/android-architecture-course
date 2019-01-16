package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question

class QuestionsListAdapter(context: Context,
                           private val mOnQuestionClickListener: OnQuestionClickListener) : ArrayAdapter<Question>(context, 0), QuestionsListItemViewMvc.Listener {
    override fun onQuestionClicked(question: Question) {
        mOnQuestionClickListener.onQuestionClicked(question)
    }

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    private data class ViewHolder(val mTitle: TextView)

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        if (convertView == null) {
            val viewMvc = QuestionsListItemViewMvcImpl(LayoutInflater.from(context), parent)
            convertView = viewMvc.getRootView()
            convertView.tag = viewMvc
        }

        val question = getItem(position)

        val viewMvc = convertView.tag as QuestionsListItemViewMvcImpl
        viewMvc.registerListener(this)
        viewMvc.bindQuestion(question)

        return convertView
    }
}
