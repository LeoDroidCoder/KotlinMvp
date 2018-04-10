package com.leonidustenko.architecture.data.datasource.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.leonidustenko.architecture.data.model.user.db.DBUser

/**
 * Created by leonid on 3/2/18.
 */
@Database(entities = [DBUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
