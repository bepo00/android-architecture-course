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
                           private val mOnQuestionClickListener: OnQuestionClickListener) : ArrayAdapter<Question>(context, 0) {

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    private data class ViewHolder(val mTitle: TextView)

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_question_list_item, parent, false)
            val viewHolder = ViewHolder(convertView!!.findViewById(R.id.txt_title))
            convertView.tag = viewHolder
        }

        val question = getItem(position)

        // bind the data to views
        val viewHolder = convertView.tag as ViewHolder
        viewHolder.mTitle.text = question!!.title

        // set listener
        convertView.setOnClickListener { onQuestionClicked(question) }

        return convertView
    }

    private fun onQuestionClicked(question: Question) {
        mOnQuestionClickListener.onQuestionClicked(question)
    }
}
