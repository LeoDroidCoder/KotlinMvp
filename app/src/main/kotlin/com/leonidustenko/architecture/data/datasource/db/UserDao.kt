package com.leonidustenko.architecture.data.datasource.db

import android.arch.persistence.room.*
import com.leonidustenko.architecture.data.model.user.db.DBUser
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: DBUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<DBUser>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: DBUser)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUsers(user: List<DBUser>)

    @Query("SELECT * FROM user " +
            "WHERE id > :lastUserId " +
            "ORDER BY id ASC " +
            "LIMIT :pageSize")
    fun getUsersPage(lastUserId: Long, pageSize: Int): Flowable<List<DBUser>>

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flowable<List<DBUser>>

    @Query("SELECT * FROM user " +
            "WHERE login LIKE :login " +
            "LIMIT 1")
    fun getUserByLogin(login: String): Flowable<DBUser>

    @Delete
    fun deleteUser(user: DBUser)

    @Delete
    fun deleteUsers(users: List<DBUser>)

    @Query("DELETE FROM user")
    fun deleteAllUsers()
}
