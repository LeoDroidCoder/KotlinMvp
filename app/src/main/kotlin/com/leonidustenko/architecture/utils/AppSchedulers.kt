package com.leonidustenko.architecture.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by leonid on 2018-03-11.
 */
object AppSchedulers {

    interface SchedulerProvider {
        fun mainThread(): Scheduler

        fun io(): Scheduler

        fun computation(): Scheduler
    }

    private var instance: SchedulerProvider = DefaultSchedulerProvider()

    fun setInstance(instance: SchedulerProvider) {
        AppSchedulers.instance = instance
    }

    fun mainThread(): Scheduler {
        return instance.mainThread()
    }

    fun io(): Scheduler {
        return instance.io()
    }

    fun computation(): Scheduler {
        return instance.computation()
    }


    class DefaultSchedulerProvider : SchedulerProvider {

        override fun mainThread(): Scheduler {
            return AndroidSchedulers.mainThread()
        }

        override fun io(): Scheduler {
            return Schedulers.io()
        }

        override fun computation(): Scheduler {
            return Schedulers.computation()
        }
    }
}