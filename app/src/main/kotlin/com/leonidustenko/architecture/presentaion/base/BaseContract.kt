package com.leonidustenko.architecture.presentaion.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.leonidustenko.architecture.presentaion.error.ErrorMessage

/**
 * Created by leonid on 2/3/18.
 */
interface BaseContract {

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View : MvpView {

        @StateStrategyType(SkipStrategy::class)
        fun onError(errorCode: ErrorMessage)
    }

    interface Presenter {

        fun onBackPressed()
    }
}