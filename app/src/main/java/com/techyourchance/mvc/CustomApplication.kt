package com.techyourchance.mvc

import android.app.Application
import com.techyourchance.mvc.common.dependencyinjection.CompositionRoot

class CustomApplication : Application() {

    lateinit var mCompositionRoot: CompositionRoot
    private set

    override fun onCreate() {
        super.onCreate()
        mCompositionRoot = CompositionRoot()
    }
}