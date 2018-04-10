package com.leonidustenko.architecture.data.model.user

/**
 * Created by leonid on 3/2/18.
 */
data class User(
        val id: Long = -1L,
        val login: String?,
        val avatarUrl: String?,
        val gravatarId: String?,
        val url: String?,
        val htmlUrl: String?,
        val followersUrl: String?,
        val followingUrl: String?,
        val gistsUrl: String?,
        val starredUrl: String?,
        val subscriptionsUrl: String?,
        val organizationsUrl: String?,
        val reposUrl: String?,
        val eventsUrl: String?,
        val receivedEventsUrl: String?,
        val type: String,
        val siteAdmin: Boolean = false,
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
