package com.leonidustenko.architecture.di

import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.Gson
import com.leonidustenko.architecture.BuildConfig
import com.leonidustenko.architecture.data.datasource.db.AppDatabase
import com.leonidustenko.architecture.data.datasource.net.RetrofitService
import com.leonidustenko.architecture.data.datasource.net.factory.RetrofitServiceFactory
import com.leonidustenko.architecture.data.repository.user.UserRepository
import com.leonidustenko.architecture.data.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by leonid on 2/3/18.
 */
@Module(includes = [NavigationModule::class])
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        val builder = Room.databaseBuilder(context, AppDatabase::class.java, "leo_architecture")
        if (BuildConfig.DEBUG)
            builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(gson: Gson): RetrofitService =
            RetrofitServiceFactory(BuildConfig.API_HOST, gson, context).create()

    @Provides
    @Singleton
    fun provideUserRepository(repo: UserRepositoryImpl): UserRepository = repo

    //todo use separate module or factory
//    @Provides
//    @Singleton
//    fun provideUsersListPresenter(presenter: UsersListPresenter): UsersListContract.Presenter = presenter
//
//    @Provides
//    @Singleton
//    fun provideUserDetailsPresenter(presenter: UserDetailsPresenter): UserDetailsContract.Presenter = presenter


}