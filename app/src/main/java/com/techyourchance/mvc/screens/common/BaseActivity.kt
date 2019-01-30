package com.techyourchance.mvc.screens.common

import android.support.v7.app.AppCompatActivity
import com.techyourchance.mvc.common.CustomApplication
import com.techyourchance.mvc.common.dependencyinjection.ControllerCompositionRoot

open class BaseActivity : AppCompatActivity(){
    val mCompositionRoot: ControllerCompositionRoot by lazy {
        ControllerCompositionRoot((application as CustomApplication).mCompositionRoot, this)
    }
}
