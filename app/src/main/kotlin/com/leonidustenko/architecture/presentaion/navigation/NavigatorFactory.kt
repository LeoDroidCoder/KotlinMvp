package com.leonidustenko.architecture.presentaion.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator

/**
 * Created by leonid on 1/11/18.
 */
object NavigatorFactory {

    fun create(fm: FragmentManager, activity: AppCompatActivity, container: Int): Navigator {
        return object : SupportFragmentNavigator(fm, container) {

            /**
             * Creates Fragment matching [screenKey].
             */
            override fun createFragment(screenKey: String?, data: Any?): Fragment =
                    FragmentsFactory.create(screenKey, data)

            /**
             * Called when we try to back from the root.
             */
            override fun exit() {
                activity.finish()
            }

            /**
             * Shows system message.

             * @param message message to show
             */
            override fun showSystemMessage(message: String) {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
