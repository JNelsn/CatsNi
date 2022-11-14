package com.example.catsni.rest

import com.example.catsni.controller.ApiController
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    const val TAG = "API ERROR"

    val api: ApiController by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiController::class.java)
    }
}