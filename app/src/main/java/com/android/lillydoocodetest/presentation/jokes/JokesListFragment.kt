package com.android.lillydoocodetest.presentation.jokes

import com.android.lillydoocodetest.R
import com.android.lillydoocodetest.databinding.FragmentJokesListBinding
import com.android.lillydoocodetest.domain.Jokes
import com.android.lillydoocodetest.util.Variables

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.lillydoocodetest.presentation.OnJokesDialogCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesListFragment : Fragment(),OnJokesAdapterListener {

    private lateinit var fragmentJokesListBinding: FragmentJokesListBinding
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private var mCallback: OnJokesDialogCallback? = null
    private var adapter: JokesListAdapter? = null
    private var toolbar: Toolbar? = null
    private val viewModel: JokesListViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnJokesDialogCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnJokesDialogCallback!")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = JokesListAdapter(this)
        viewModel.loadJokesList(10)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentJokesListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_jokes_list, container, false)
        fragmentJokesListBinding.jokesListViewModel = viewModel
        fragmentJokesListBinding.jokesRecyclerView.adapter = adapter

        toolbar = fragmentJokesListBinding.root.findViewById(R.id.toolbar) as Toolbar
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar?.title = getString(R.string.str_jokes)

        //** Set the Layout Manager of the RecyclerView
        setRVLayoutManager()
        setupObservers()
        pullToRefreshJokesList()

        return fragmentJokesListBinding.root
    }

    private fun pullToRefreshJokesList() {
        fragmentJokesListBinding.swipeContainer.setOnRefreshListener {
            viewModel.loadJokesList(10)
        }

    }

    private fun setRVLayoutManager() {
        mLayoutManager = LinearLayoutManager(requireContext())
        fragmentJokesListBinding.jokesRecyclerView.layoutManager = mLayoutManager
        fragmentJokesListBinding.jokesRecyclerView.setHasFixedSize(true)
        fragmentJokesListBinding.jokesRecyclerView.itemAnimator = null
    }


    private fun setupObservers() {

        if (Variables.isNetworkConnected) {
            viewModel.isLoad.observe(viewLifecycleOwner, {
                it?.let { visibility ->
                    fragmentJokesListBinding.progressBar.visibility =
                        if (visibility) View.GONE else View.VISIBLE
                    fragmentJokesListBinding.jokesRecyclerView.visibility = View.VISIBLE
                    fragmentJokesListBinding.noNetwork.visibility = View.GONE
                }
            })

            viewModel.jokesReceivedLiveData.observe(viewLifecycleOwner, {
                it?.let {
                    initRecyclerView(it)
                    fragmentJokesListBinding.swipeContainer.isRefreshing = false
                }
            })
        } else {
            viewModel.isLoad.value = true
            viewModel.isLoad.observe(viewLifecycleOwner, {
                it?.let { visibility ->
                    fragmentJokesListBinding.progressBar.visibility =
                        if (visibility) View.GONE else View.VISIBLE
                    fragmentJokesListBinding.jokesRecyclerView.visibility = View.GONE
                    fragmentJokesListBinding.noNetwork.visibility = View.VISIBLE
                }
            })
        }
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
        mCallback = null
    }

    companion object {

        val FRAGMENT_NAME: String = JokesListFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            JokesListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


    private fun initRecyclerView(jokesList: List<Jokes>) {
        adapter?.updateData(jokesList)
    }

    override fun showJokesList(jokes: Jokes) {
        mCallback?.navigateToJokesDialogPage(jokes)
    }
}