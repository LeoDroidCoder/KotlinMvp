package com.leonidustenko.architecture.data.repository.user

import com.leonidustenko.architecture.data.model.user.User
import io.reactivex.Flowable
import retrofit2.http.Path

/**
 * Created by leonid on 3/2/18.
 */
interface UserRepository {


    /**
     * Get a page of users
     */
    fun getUsersPage(lastUserId: Long): Flowable<List<User>>

    /**
     * Get page size (number of items to be loaded in a batch)
     */
    fun getPageSize(): Int

    /**
     * Get full user entity by a username
     *
     * @param login user name (login)
     * @return detailed user [User]
     */
    fun getUserDetails(@Path("login") login: String): Flowable<User>
}
