package com.techyourchance.mvc.screens.questiondetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.BaseViewMvc

class QuestionDetailsViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) : BaseViewMvc(), QuestionDetailsViewMvc{
    private val txtTitle: TextView
    private val txtBody: TextView

    init {
        setRootView(inflater.inflate(R.layout.activity_question_details, parent, false))
        txtTitle = findViewById(R.id.txt_title)
        txtBody = findViewById(R.id.txt_body)
    }

    override fun bindQuestionDetails(questionDetails: QuestionDetails) {
        txtTitle.text = questionDetails.title
        txtBody.text = questionDetails.body
    }
}