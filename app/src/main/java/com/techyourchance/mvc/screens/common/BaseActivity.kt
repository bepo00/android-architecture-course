package com.techyourchance.mvc.screens.common

import android.support.v7.app.AppCompatActivity
import com.techyourchance.mvc.CustomApplication
import com.techyourchance.mvc.common.dependencyinjection.CompositionRoot

open class BaseActivity : AppCompatActivity(){

    protected fun getCompositionRoot(): CompositionRoot {
        return (application as CustomApplication).mCompositionRoot
    }
}
