package com.techyourchance.mvc.questions

import com.techyourchance.mvc.common.BaseObservable
import com.techyourchance.mvc.common.Constants
import com.techyourchance.mvc.networking.QuestionSchema
import com.techyourchance.mvc.networking.QuestionsListResponseSchema
import com.techyourchance.mvc.networking.StackoverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class FetchLastActiveQuestionsUseCase(
        private val mStackoverflowApi: StackoverflowApi
) : BaseObservable<FetchLastActiveQuestionsUseCase.Listener>() {
    interface Listener {
        fun onLastActiveQuestionsFetched(questions: ArrayList<Question>)
        fun onLastActiveQuestionsFetchFailed()

    }

    fun fetchLastActiveQuestionsAndNotify() {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(object : Callback<QuestionsListResponseSchema> {
                    override fun onResponse(call: Call<QuestionsListResponseSchema>, response: Response<QuestionsListResponseSchema>) {
                        if (response.isSuccessful) {
                            notifySuccess(response.body()!!.questions)
                        } else {
                            notifyFailure()
                        }
                    }

                    override fun onFailure(call: Call<QuestionsListResponseSchema>, t: Throwable) {
                        notifyFailure()
                    }
                })
    }

    private fun notifySuccess(questionSchemas: List<QuestionSchema>){
        val questions = ArrayList<Question>(questionSchemas.size)
        for (questionSchema in questionSchemas) {
            questions.add(Question(questionSchema.id, questionSchema.title))
        }
        getListeners().forEach { it.onLastActiveQuestionsFetched(questions) }
    }

    private fun notifyFailure(){
        getListeners().forEach { it.onLastActiveQuestionsFetchFailed() }
    }
}