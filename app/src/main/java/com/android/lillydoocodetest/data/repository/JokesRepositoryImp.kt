package com.android.lillydoocodetest.data.repository

import com.android.lillydoocodetest.data.RetrofitService
import com.android.lillydoocodetest.domain.Response
import com.android.lillydoocodetest.domain.repository.JokesRepository

/**
 * This repository is responsible for
 * fetching data[jokesList] from server
 * */
class JokesRepositoryImp(
    private val retrofitService: RetrofitService
) :
    JokesRepository {
    override suspend fun getJokes(
        amount: Int?
    ): Response {
        return retrofitService.getJokesData(amount)
    }
}