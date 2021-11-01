package com.android.lillydoocodetest.presentation.jokes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.lillydoocodetest.domain.Jokes

class JokesDetailViewModel : ViewModel() {

    private val _selectedJokesData = MutableLiveData<Jokes>()
    val selectedJokesData: MutableLiveData<Jokes> = _selectedJokesData
    val isHideSetupAndPunchlineView = MutableLiveData<Boolean>()

    init {
        isHideSetupAndPunchlineView.value = false
    }

    fun setInitialData(
        jokes: Jokes
    ) {
        _selectedJokesData.value = jokes
    }

    fun setJoke(jokes: Jokes) {
        if (jokes.type == "single") {
            isHideSetupAndPunchlineView.value = true
        }
    }
}