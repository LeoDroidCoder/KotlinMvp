package com.leonidustenko.architecture.data.repository.user

import com.leonidustenko.architecture.data.datasource.db.AppDatabase
import com.leonidustenko.architecture.data.datasource.net.RetrofitService
import com.leonidustenko.architecture.data.model.user.User
import com.leonidustenko.architecture.data.model.user.db.DbUserMapper
import com.leonidustenko.architecture.data.model.user.net.NetUserMapper
import com.leonidustenko.architecture.utils.AppSchedulers
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by leonid on 3/2/18.
 */
@Singleton
class UserRepositoryImpl @Inject constructor(
        private val database: AppDatabase,
        private val retrofitService: RetrofitService
) : UserRepository {

    override fun getUsersPage(lastUserId: Long): Flowable<List<User>> {
        val localData = database.userDao().getUsersPage(lastUserId, USERS_PAGE_SIZE)
                .subscribeOn(AppSchedulers.computation())
                .map { DbUserMapper.mapTo(it) }

        val remoteData = retrofitService.getUsers(lastUserId)
                .subscribeOn(AppSchedulers.io())
                .observeOn(AppSchedulers.computation())
                .map { NetUserMapper.mapTo(it) }
                .map {
                    // cache users to db
                    database.userDao().insertUsers(DbUserMapper.mapFrom(it))
                    it
                }

        return Flowable.mergeDelayError(localData, remoteData)
    }

    override fun getPageSize(): Int = USERS_PAGE_SIZE

    override fun getUserDetails(login: String): Flowable<User> {
        val local = database.userDao().getUserByLogin(login)
                .subscribeOn(AppSchedulers.computation())
                .map { DbUserMapper.mapTo(it) }

        val remote = retrofitService.getUserDetails(login)
                .subscribeOn(AppSchedulers.io())
                .observeOn(AppSchedulers.computation())
                .map { NetUserMapper.mapTo(it) }
                .map {
                    // cache user to db
                    database.userDao().insertUser(DbUserMapper.mapFrom(it))
                    it
                }

        return Flowable.mergeDelayError(local, remote)
    }


    private companion object {
        const val USERS_PAGE_SIZE = 30
    }
}