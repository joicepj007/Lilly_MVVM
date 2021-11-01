package com.android.lillydoocodetest.presentation.jokes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.lillydoocodetest.domain.Jokes
import com.android.lillydoocodetest.domain.usecase.GetJokesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**To store & manage UI-related data in a lifecycle conscious way!
 * this class allows data to survive configuration changes such as screen rotation
 * by interacting with [GetJokesListUseCase]
 *
 * */
class JokesListViewModel @ViewModelInject constructor(private val getJokesListUseCase: GetJokesListUseCase) : ViewModel() {

    val jokesReceivedLiveData = MutableLiveData<List<Jokes>>()
    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }


fun loadJokesList(amount: Int?) {
    viewModelScope.launch {
        try {
            getJokesListUseCase.saveAmount(amount)
            getJokesListUseCase.getData().let {
                isLoad.value = true
                jokesReceivedLiveData.value = it.jokes
            }
        } catch (exception: Exception) {
        }

    }
}

}