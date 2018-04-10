package com.leonidustenko.architecture.presentaion.common.adapter.diffutil

import com.leodroidcoder.genericadapter.BaseDiffCallback
import com.leonidustenko.architecture.data.model.user.User

class UserDiffCallback(oldItems: List<User>, newItems: List<User>) : BaseDiffCallback<User>(oldItems, newItems) {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return newItem.login == oldItem.login
    }
}
