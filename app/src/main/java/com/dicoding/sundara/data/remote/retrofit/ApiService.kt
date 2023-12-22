package com.dicoding.sundara.data.remote.retrofit

import com.dicoding.sundara.data.remote.response.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/headlines")
    fun getTopHeadlines(@Query("category") category: String): Call<ApiResponse>
}