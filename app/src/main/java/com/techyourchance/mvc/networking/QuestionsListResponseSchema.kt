package com.techyourchance.mvc.networking

import com.google.gson.annotations.SerializedName

class QuestionsListResponseSchema(@field:SerializedName("items")
                                  val questions: List<QuestionSchema>)
