package com.techyourchance.mvc.networking

import com.google.gson.annotations.SerializedName
import com.techyourchance.mvc.questions.Question
import java.util.*

class QuestionDetailsResponseSchema(question: QuestionSchema) {
    @SerializedName("items")
    private val mQuestions = Collections.singletonList(question)

    fun getQuestion(): QuestionSchema {
        return mQuestions[0]
    }
}