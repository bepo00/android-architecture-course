package com.techyourchance.mvc.screens.questiondetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.techyourchance.mvc.R
import android.content.Intent
import android.content.Context


class QuestionDetailsActivity : AppCompatActivity() {


    companion object {
        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_details)
    }
}
