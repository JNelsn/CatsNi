package com.example.catsni.controller

import com.example.catsni.ApiData
import com.example.catsni.ApiDataItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiController {



    @GET("/v1/images/search")
    suspend fun  getRandomCat(): Response<ApiData>
}