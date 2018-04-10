package com.leonidustenko.architecture.presentaion.common.adapter

import android.content.Context
import android.view.ViewGroup
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import com.leonidustenko.architecture.R
import com.leonidustenko.architecture.data.model.user.User
import com.leonidustenko.architecture.presentaion.common.adapter.diffutil.UserDiffCallback
import com.leonidustenko.architecture.presentaion.common.adapter.viewholder.UserViewHolder



/**
 * Created by leonid on 2018-03-11.
 */
class UsersAdapter(context: Context?, listener: OnRecyclerItemClickListener) : GenericRecyclerViewAdapter<User, OnRecyclerItemClickListener, UserViewHolder>(context, listener) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {
        return UserViewHolder(inflate(R.layout.item_user, parent), listener)
    }

    override fun updateItems(newItems: List<User>) {
        // initialise users diff callback, create above
        val diffCallback = UserDiffCallback(items, newItems)
        // pass to parent adapter to let it do all the remaining job
        super.updateItems(newItems, diffCallback)
    }
}
