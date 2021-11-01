package com.android.lillydoocodetest.util

interface IPreferenceHelper {
    fun setAppOpenedCountKey(count: Int)
    fun getAppOpenedCountKey(): Int
    fun clearPrefs()

}