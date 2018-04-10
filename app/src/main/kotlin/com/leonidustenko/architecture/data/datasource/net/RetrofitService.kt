package com.leonidustenko.architecture.data.datasource.net

import com.leonidustenko.architecture.data.model.user.net.NetUser
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    /**
     * Get a list of users.
     * Returns a List of Users (short version). In order to fetch detailed entity fetch it by using
     * [#getUserDetails()]
     *
     * @param sinceUserId id of the last user. Group of consecutive users will be loaded.
     * Pass in 0 to load users from the beginning.
     * @return observable list of [NetUser]
     */
    @GET("users")
    fun getUsers(@Query("since") sinceUserId: Long): Flowable<List<NetUser>>

    /**
     * Get full user entity by a username
     *
     * @param login user name (login)
     * @return detailed user [NetUser]
     */
    @GET("users/:{login}")
    fun getUserDetails(@Path("login") login: String): Flowable<NetUser>

}
