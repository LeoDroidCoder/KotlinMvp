package com.leonidustenko.architecture.di

import com.leonidustenko.architecture.presentaion.screens.container.ContainerActivity
import com.leonidustenko.architecture.presentaion.screens.usersdetails.UserDetailsFragment
import com.leonidustenko.architecture.presentaion.screens.userslist.UsersListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by leonid on 2/3/18.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: ContainerActivity)
    fun inject(fragment: UsersListFragment)
    fun inject(fragment: UserDetailsFragment)
}