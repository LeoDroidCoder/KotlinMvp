package com.leonidustenko.architecture.data.model.user.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.leonidustenko.architecture.data.datasource.db.AppDatabase

/**
 * Database-level user model.
 * Used to store users in [AppDatabase]
 */
@Entity(tableName = "user")
data class DBUser(
        @PrimaryKey var id: Long = -1L,
        var login: String?,
        var avatarUrl: String?,
        var gravatarId: String?,
        var url: String?,
        var htmlUrl: String?,
        var followersUrl: String?,
        var followingUrl: String?,
        var gistsUrl: String?,
        var starredUrl: String?,
        var subscriptionsUrl: String?,
        var organizationsUrl: String?,
        var reposUrl: String?,
        var eventsUrl: String?,
        var receivedEventsUrl: String?,
        var type: String,
        var siteAdmin: Boolean = false,
        val name: String?,
        val company: String?,
        val blog: String?,
        val location: String?,
        val email: String?,
        val hireable: Boolean = false,
        val bio: String?,
        val publicRepos: Int = -1,
        val publicGists: Int = -1,
        val followers: Int = -1,
        val following: Int = -1,
        val createdAt: String?,
        val updatedAt: String?
)
