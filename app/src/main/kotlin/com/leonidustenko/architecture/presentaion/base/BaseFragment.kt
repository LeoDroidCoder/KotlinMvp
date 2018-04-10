package com.leonidustenko.architecture.presentaion.base

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpAppCompatFragment
import com.leonidustenko.architecture.presentaion.error.ErrorMessage
import com.leonidustenko.architecture.presentaion.navigation.RouterProvider
import ru.terrakok.cicerone.Router

/**
 * Created by leonid on 2/3/18.
 */
abstract class BaseFragment<out P : BaseContract.Presenter> : MvpAppCompatFragment(), RouterProvider {

    abstract fun providePresenter(): P

//    abstract fun getPresenter(): P

    override fun getRouter(): Router {
        return (activity as RouterProvider).getRouter()
    }

    protected fun showToast(@StringRes stringRes: Int) {
        (activity as? BaseActivity<*>)?.showToast(stringRes)
    }

    protected fun showToast(message: String) {
        (activity as? BaseActivity<*>)?.showToast(message)
    }

    protected fun handleError(errorCode: ErrorMessage) {
        (activity as? BaseActivity<*>)?.handleError(errorCode)
    }

//    fun onBackButtonPressed(): Boolean {
//        getPresenter().onBackPressed()
//        return true
//    }

}