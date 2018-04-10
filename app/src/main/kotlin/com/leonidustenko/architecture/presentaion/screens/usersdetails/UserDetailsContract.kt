package com.leonidustenko.architecture.presentaion.screens.usersdetails

import com.leonidustenko.architecture.presentaion.base.BaseContract

/**
 * Created by leonid on 3/5/18.
 */
interface UserDetailsContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter {

    }

    class Params(
            val userLogin: String
    )
}