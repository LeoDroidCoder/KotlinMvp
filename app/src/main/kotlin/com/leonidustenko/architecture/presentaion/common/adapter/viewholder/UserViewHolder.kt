package com.leonidustenko.architecture.presentaion.common.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.leodroidcoder.genericadapter.BaseViewHolder
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import com.leonidustenko.architecture.data.model.user.User
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Created by leonid on 2018-03-11.
 */
class UserViewHolder(itemView: View, listener: OnRecyclerItemClickListener?) : BaseViewHolder<User, OnRecyclerItemClickListener>(itemView, listener) {

    private val nameTv: TextView? = itemView.tv_login

    init {
        listener?.let {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }

    override fun onBind(item: User) {
        nameTv?.text = item.login
    }
}