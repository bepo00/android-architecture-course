package com.techyourchance.mvc.common.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.screens.common.ViewMvcFactory

class ControllerCompositionRoot(compositionRoot: CompositionRoot, activity: Activity){
    private val mCompositionRoot: CompositionRoot = compositionRoot
    private val mActivity: Activity = activity

    private fun getStackOverflowApi(): StackoverflowApi {
        return mCompositionRoot.getStackOverflowApi()
    }

    private fun getLayoutInflater(): LayoutInflater {
        return mActivity.layoutInflater
    }

    fun getViewMcvFactory(): ViewMvcFactory {
        return ViewMvcFactory(getLayoutInflater())
    }

    fun getFetchQuestionDetailsUseCase(): FetchQuestionDetailsUseCase {
        return FetchQuestionDetailsUseCase(getStackOverflowApi())
    }

    fun getFetchLastActiveQuestionsUseCase(): FetchLastActiveQuestionsUseCase {
        return FetchLastActiveQuestionsUseCase(getStackOverflowApi())
    }
}