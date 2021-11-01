package com.android.lillydoocodetest.presentation.jokes

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.android.lillydoocodetest.R
import com.android.lillydoocodetest.databinding.FragmentJokesDetailsBinding
import com.android.lillydoocodetest.domain.Jokes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentJokesDetailsBinding
    private val viewModel: JokesDetailViewModel by viewModels()

    companion object {
        const val KEY_JOKE = "joke"

        @JvmStatic
        fun newInstance(jokes: Jokes) =
            JokesDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_JOKE, jokes)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_jokes_details,
            container,
            false
        )
        this.binding.jokesDetailViewModel = viewModel
        this.binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_NoActionBar)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.50).toInt()
        dialog!!.window?.setLayout(width, height)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainTypeValue = arguments?.getParcelable<Jokes?>(KEY_JOKE)
        mainTypeValue?.let { viewModel.setInitialData(it) }
        mainTypeValue?.let { viewModel.setJoke(it) }
        setupObservers()
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        dialog?.setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (event.action == KeyEvent.ACTION_UP) {
                    dismiss()
                }
                true
            } else {
                false
            }
        }
    }

    private fun setupObservers() {
        viewModel.isHideSetupAndPunchlineView.observe(viewLifecycleOwner, {
            it?.let { visibility ->
                if (visibility) {
                    binding.tvJokesSetup.visibility = View.GONE
                    binding.tvJokesPunchline.visibility = View.GONE
                    binding.tvJokesJoke.visibility = View.VISIBLE
                } else {
                    binding.tvJokesSetup.visibility = View.VISIBLE
                    binding.tvJokesPunchline.visibility = View.VISIBLE
                    binding.tvJokesJoke.visibility = View.GONE
                }
            }
        })
    }
}
