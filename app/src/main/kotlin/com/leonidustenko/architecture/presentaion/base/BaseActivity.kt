package com.leonidustenko.architecture.presentaion.base

import android.support.annotation.StringRes
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.leonidustenko.architecture.presentaion.error.ErrorMessage
import com.leonidustenko.architecture.presentaion.error.ErrorMessageFactory

/**
 * Created by leonid on 2/3/18.
 */
abstract class BaseActivity<out P : BaseContract.Presenter> : MvpAppCompatActivity() {

//    @Inject
////    @InjectPresenter
//    lateinit var presenter: P
//
//    @ProvidePresenter
//    fun providePresenter() = presenter

    abstract fun providePresenter(): P

    fun showToast(@StringRes stringRes: Int) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Retrieves error message by error code and shows it.
     *
     * @param errorCode error code
     * @see [ErrorMessage]
     * @see [ErrorMessageFactory]
     */
    fun handleError(errorCode: ErrorMessage) {
        showToast(ErrorMessageFactory.create(this, errorCode))
    }
}
