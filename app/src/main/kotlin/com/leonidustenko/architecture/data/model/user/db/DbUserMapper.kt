package com.leonidustenko.architecture.data.model.user.db

import com.leonidustenko.architecture.data.mapper.ObjectMapper
import com.leonidustenko.architecture.data.model.user.User

/**
 * Mapper used to transform database-level users [DBUser] into domain-lave user models [User] and vice versa
 */
object DbUserMapper : ObjectMapper<DBUser, User> {

    override fun mapTo(a: DBUser): User {
        return User(
                id = a.id,
                login = a.login,
                avatarUrl = a.avatarUrl,
                gravatarId = a.gravatarId,
                url = a.url,
                htmlUrl = a.htmlUrl,
                followersUrl = a.followersUrl,
                followingUrl = a.followingUrl,
                gistsUrl = a.gistsUrl,
                starredUrl = a.starredUrl,
                subscriptionsUrl = a.subscriptionsUrl,
                organizationsUrl = a.organizationsUrl,
                reposUrl = a.reposUrl,
                eventsUrl = a.eventsUrl,
                receivedEventsUrl = a.receivedEventsUrl,
                type = a.type,
                siteAdmin = a.siteAdmin,
                name = a.name,
                company = a.company,
                blog = a.blog,
                location = a.location,
                email = a.email,
                hireable = a.hireable,
                bio = a.bio,
                publicRepos = a.publicRepos,
                publicGists = a.publicGists,
                followers = a.followers,
                following = a.following,
                createdAt = a.createdAt,
                updatedAt = a.updatedAt
        )
    }

    override fun mapFrom(b: User): DBUser {
        return DBUser(
                id = b.id,
                login = b.login,
                avatarUrl = b.avatarUrl,
                gravatarId = b.gravatarId,
                url = b.url,
                htmlUrl = b.htmlUrl,
                followersUrl = b.followersUrl,
                followingUrl = b.followingUrl,
                gistsUrl = b.gistsUrl,
                starredUrl = b.starredUrl,
                subscriptionsUrl = b.subscriptionsUrl,
                organizationsUrl = b.organizationsUrl,
                reposUrl = b.reposUrl,
                eventsUrl = b.eventsUrl,
                receivedEventsUrl = b.receivedEventsUrl,
                type = b.type,
                siteAdmin = b.siteAdmin,
                name = b.name,
                company = b.company,
                blog = b.blog,
                location = b.location,
                email = b.email,
                hireable = b.hireable,
                bio = b.bio,
                publicRepos = b.publicRepos,
                publicGists = b.publicGists,
                followers = b.followers,
                following = b.following,
                createdAt = b.createdAt,
                updatedAt = b.updatedAt
        )
    }
}