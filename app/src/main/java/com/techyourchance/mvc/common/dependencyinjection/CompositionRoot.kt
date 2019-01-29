package com.techyourchance.mvc.common.dependencyinjection

import com.techyourchance.mvc.common.Constants
import com.techyourchance.mvc.networking.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot {
    private val mRetrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getStackOverflowApi(): StackoverflowApi {
        return mRetrofit.create(StackoverflowApi::class.java)
    }
}