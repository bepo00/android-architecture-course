package com.techyourchance.mvc.screens.questionslist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.ViewMvcFactory

class QuestionsListAdapter(private val mOnQuestionClickListener: OnQuestionClickListener,
                           private val viewMvcFactory: ViewMvcFactory) : RecyclerView.Adapter<QuestionsListAdapter.ViewHolder>(), QuestionsListItemViewMvc.Listener {

    data class ViewHolder(val viewMvc: QuestionsListItemViewMvc) : RecyclerView.ViewHolder(viewMvc.getRootView())

    private var mQuestions: List<Question> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val viewMvc = viewMvcFactory.getQuestionsListItemViewMvc(viewGroup)
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
