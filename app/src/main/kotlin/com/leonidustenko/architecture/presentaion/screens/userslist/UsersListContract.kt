package com.leonidustenko.architecture.presentaion.screens.userslist

import com.leonidustenko.architecture.data.model.user.User
import com.leonidustenko.architecture.presentaion.base.BaseContract

/**
 * Created by leonid on 3/5/18.
 */
interface UsersListContract {

    interface View : BaseContract.View {
        /**
         * Presenter returned list of Users
         */
        fun onUsers(users: List<User>)
    }

    interface Presenter : BaseContract.Presenter {

        fun onScrolled(visibleItemCount: Int, totalItemCount: Int, firstVisibleItemPosition: Int)

        /**
         * A user item has been clicked.
         * Navigate to the details screen.
         */
        fun onUserClicked(user: User)
    }
}