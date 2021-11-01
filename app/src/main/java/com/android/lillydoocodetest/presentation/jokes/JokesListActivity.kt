package com.android.lillydoocodetest.presentation.jokes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.lillydoocodetest.R
import com.android.lillydoocodetest.domain.Jokes
import com.android.lillydoocodetest.presentation.OnJokesDialogCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesListActivity : AppCompatActivity(),OnJokesDialogCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToJokesListPage()
    }

    private fun navigateToJokesListPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.flFragment,
                JokesListFragment.newInstance(),
                JokesListFragment.FRAGMENT_NAME
            ).commit()
    }

    override fun navigateToJokesDialogPage(jokes: Jokes) {
        val jokesDialogFragment =
            JokesDialogFragment.newInstance(jokes)
        jokesDialogFragment.show(
            supportFragmentManager,
            JokesDialogFragment::class.java.canonicalName
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }
}