package com.leonidustenko.architecture.presentaion.screens.usersdetails

import com.arellomobile.mvp.InjectViewState
import com.leonidustenko.architecture.presentaion.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by leonid on 3/5/18.
 */
@InjectViewState
class UserDetailsPresenter
@Inject constructor(
        private val router: Router?
) : BasePresenter<UserDetailsContract.View>(), UserDetailsContract.Presenter {

    override fun onBackPressed() {
        router?.exit()
    }
}
