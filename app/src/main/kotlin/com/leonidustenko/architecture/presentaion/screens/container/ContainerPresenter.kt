package com.leonidustenko.architecture.presentaion.screens.container

import com.arellomobile.mvp.InjectViewState
import com.leonidustenko.architecture.presentaion.base.BasePresenter
import com.leonidustenko.architecture.presentaion.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by leonid on 2/3/18.
 */
@InjectViewState
class ContainerPresenter @Inject constructor(
        private val router: Router
) : BasePresenter<ContainerContract.View>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screens.SCREEN_LIST)
    }

    override fun onBackPressed() {
        router.exit()
    }
}
