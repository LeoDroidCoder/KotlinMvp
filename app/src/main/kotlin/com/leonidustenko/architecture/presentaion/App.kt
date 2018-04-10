package com.leonidustenko.architecture.presentaion

import android.app.Application
import com.leonidustenko.architecture.data.datasource.net.exception.NetworkConnectionException
import com.leonidustenko.architecture.di.AppComponent
import com.leonidustenko.architecture.di.AppModule
import com.leonidustenko.architecture.di.DaggerAppComponent
import io.reactivex.plugins.RxJavaPlugins

/**
 * Created by leonid on 2/3/18.
 */
class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
        setRxErrorHandler()
    }

    private fun initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    /**
     * Sets RxJava2 error handler which is used to handle errors on disposed streams.
     * Basically here you should decide which ones should crash the App and which not.
     *
     * @see [RxJava2 Error handling](https://githu.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling)
     */
    private fun setRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            if (e.cause !is NetworkConnectionException)
                throw e
        }
    }
}
