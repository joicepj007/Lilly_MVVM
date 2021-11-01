package com.android.lillydoocodetest.presentation.jokes

import com.android.lillydoocodetest.domain.Jokes

/**
 * To make an interaction between [JokesListAdapter] & [JokesListFragment]
 * */
interface OnJokesAdapterListener {

    fun showJokesList(jokes: Jokes)
}