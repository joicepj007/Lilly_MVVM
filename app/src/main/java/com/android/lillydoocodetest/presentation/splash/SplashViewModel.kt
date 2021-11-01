package com.android.lillydoocodetest.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    private val _appOpenedCount = MutableLiveData<Int?>()
    val appOpenedCount: MutableLiveData<Int?> = _appOpenedCount

    fun updateAppOpenedCount(count:Int?) {
        _appOpenedCount.value=count
    }


}