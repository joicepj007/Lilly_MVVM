package com.android.lillydoocodetest.domain.repository

import com.android.lillydoocodetest.domain.Response

/**
 * To make an interaction between [JokesRepositoryImp] & [GetJokesListUseCase]
 * */
interface JokesRepository {

   suspend fun getJokes(amount:Int?): Response
}