package com.android.lillydoocodetest.domain.usecase

import com.android.lillydoocodetest.domain.Response
import com.android.lillydoocodetest.domain.repository.JokesRepository
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [JokesListViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetJokesListUseCase @Inject constructor(private val repository: JokesRepository) {

    private var limit: Int? = null

    fun saveAmount(amount: Int?) {
        limit = amount
    }

    suspend fun getData(): Response {
        return repository.getJokes(limit)
    }
}