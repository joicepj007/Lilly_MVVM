package com.android.lillydoocodetest.presentation.jokes


import androidx.recyclerview.widget.DiffUtil
import com.android.lillydoocodetest.domain.Jokes

class JokesDiffCallBack(
    private val newItems: List<Jokes>,
    private val oldItems: List<Jokes>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return (oldItem.id == newItem.id) && (oldItem.category == newItem.category)
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem == newItem
    }
}