package com.android.lillydoocodetest.data

import com.android.lillydoocodetest.domain.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("Any")
    suspend fun getJokesData(@Query("amount") amount: Int?): Response
}