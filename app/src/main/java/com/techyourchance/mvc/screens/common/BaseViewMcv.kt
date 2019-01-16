package com.techyourchance.mvc.screens.common

import android.content.Context
import android.view.View

abstract class BaseViewMcv : ViewMvc {
    private lateinit var mRootView: View

    override fun getRootView(): View {
        return mRootView
    }

    protected fun getContext(): Context {
        return getRootView().context
    }

    protected fun setRootView(rootView: View){
        mRootView = rootView
    }

    protected fun <T : View> findViewById(id: Int): T {
        return getRootView().findViewById(id)
    }
}