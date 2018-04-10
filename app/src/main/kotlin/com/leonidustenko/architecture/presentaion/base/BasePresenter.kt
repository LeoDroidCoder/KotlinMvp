package com.leonidustenko.architecture.presentaion.base

import com.arellomobile.mvp.MvpPresenter
import com.leonidustenko.architecture.data.datasource.net.exception.NetworkConnectionException
import com.leonidustenko.architecture.presentaion.error.ErrorMessage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by leonid on 2/3/18.
 */
abstract class BasePresenter<V : BaseContract.View> : MvpPresenter<V>(), BaseContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    /**
     * Add a disposable [Disposable] to the disposables container [CompositeDisposable].
     * It lets clearing multiple ones a the same moment.
     */
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    /**
     * Clears all the disposables.
     * Is called by default when presenter is beign destroyed.
     */
    fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearDisposables()
    }

    fun handleError(error: Throwable, defaultErrorCode: ErrorMessage) {
        error.printStackTrace()
        when (error) {
            is NetworkConnectionException ->
                viewState?.onError(ErrorMessage.ERROR_INTERNET_CONNECTION)

            else -> viewState?.onError(defaultErrorCode)
        }
    }
}
