package com.android.lillydoocodetest.presentation

import com.android.lillydoocodetest.domain.Jokes

/**
 * To make an interaction between [JokesListActivity] & its children
 * */
interface OnJokesDialogCallback {

    fun navigateToJokesDialogPage(jokes: Jokes)

}