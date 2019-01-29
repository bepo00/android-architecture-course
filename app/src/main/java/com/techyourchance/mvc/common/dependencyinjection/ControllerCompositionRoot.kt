package com.techyourchance.mvc.common.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.techyourchance.mvc.networking.StackoverflowApi

class ControllerCompositionRoot(compositionRoot: CompositionRoot, activity: Activity){
    private val mCompositionRoot: CompositionRoot = compositionRoot
    private val mActivity: Activity = activity

    fun getStackOverflowApi(): StackoverflowApi {
        return mCompositionRoot.getStackOverflowApi()
    }

    fun getLayoutInflater(): LayoutInflater {
        return mActivity.layoutInflater
    }
}