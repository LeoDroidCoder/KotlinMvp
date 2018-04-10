package com.leonidustenko.architecture.presentaion.navigation

import android.support.v4.app.Fragment
import com.leonidustenko.architecture.presentaion.screens.usersdetails.UserDetailsContract
import com.leonidustenko.architecture.presentaion.screens.usersdetails.UserDetailsFragment
import com.leonidustenko.architecture.presentaion.screens.userslist.UsersListFragment

/**
 * Creates fragments by screen key [Screens]
 * Created by leonid on 1/11/18.
 */
object FragmentsFactory {

    fun create(screenKey: String?, data: Any?): Fragment {
        return when (screenKey) {
            Screens.SCREEN_LIST ->
                UsersListFragment.newInstance()
            Screens.SCREEN_DETAILS ->
                UserDetailsFragment.newInstance(data as UserDetailsContract.Params)

            else -> throw IllegalArgumentException("Unsupported screen key $screenKey")
        }
    }
}