package com.android.lillydoocodetest.presentation.jokes

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.lillydoocodetest.R
import com.android.lillydoocodetest.databinding.HolderItemBinding
import com.android.lillydoocodetest.domain.Jokes
import java.util.ArrayList

internal class JokesListAdapter(val mListener: OnJokesAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var jokesList: List<Jokes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderAlbumBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_item, parent, false
        )
        return EventViewHolder(holderAlbumBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EventViewHolder).onBind(getItem(position), position)
    }

    private fun getItem(position: Int): Jokes {
        return jokesList[position]
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }

    fun updateData(jokes: List<Jokes>) {
        val diffCallback = JokesDiffCallBack(jokes, jokesList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        jokesList = jokes.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    inner class EventViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun onBind(jokes: Jokes, position: Int) {
            val holderAlbumBinding = dataBinding as HolderItemBinding
            holderAlbumBinding.jokes = jokes
            itemView.setOnClickListener {
                mListener.showJokesList(jokes)
            }
        }
    }
}
