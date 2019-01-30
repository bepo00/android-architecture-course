package com.techyourchance.mvc.questions

import com.techyourchance.mvc.common.BaseObservable
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema
import com.techyourchance.mvc.networking.QuestionSchema
import com.techyourchance.mvc.networking.StackoverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchQuestionDetailsUseCase(
        private val mStackoverflowApi: StackoverflowApi
) : BaseObservable<FetchQuestionDetailsUseCase.Listener>() {

    interface Listener {
        fun onQuestionDetailsFetched(questionDetails: QuestionDetails)
        fun onQuestionDetailsFetchFailure()

    }

    fun fetchQuestionDetailsAndNotify(mQuestionId: String) {
        mStackoverflowApi.fetchQuestionDetails(mQuestionId)
                .enqueue(object : Callback<QuestionDetailsResponseSchema> {
                    override fun onFailure(call: Call<QuestionDetailsResponseSchema>, t: Throwable) {
                        notifyFailure()
                    }

                    override fun onResponse(call: Call<QuestionDetailsResponseSchema>, response: Response<QuestionDetailsResponseSchema>) {
                        if(response.isSuccessful){
                            notifySuccess(response.body()!!.getQuestion())
                        } else{
                            notifyFailure()
                        }
                    }

                })
    }

    private fun notifyFailure(){
        getListeners().forEach { it.onQuestionDetailsFetchFailure() }
    }

    private fun notifySuccess(questionSchema: QuestionSchema) {
        val questionDetails = QuestionDetails(questionSchema.id,
                questionSchema.title, questionSchema.body)
        getListeners().forEach {
            it.onQuestionDetailsFetched(questionDetails)
        }
    }
}