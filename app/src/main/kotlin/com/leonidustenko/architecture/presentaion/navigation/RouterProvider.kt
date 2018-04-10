package com.leonidustenko.architecture.presentaion.navigation

import ru.terrakok.cicerone.Router

/**
 * Created by leonid on 2/3/18.
 */
interface RouterProvider {

    fun getRouter() : Router
}