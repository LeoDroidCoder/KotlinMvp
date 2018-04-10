package com.leonidustenko.architecture.data.model.user.net

import com.leonidustenko.architecture.data.mapper.OneWayObjectMapper
import com.leonidustenko.architecture.data.model.user.User

/**
 * Mapper used to transform network users [NetUser] into the domain user model [User]
 */
object NetUserMapper : OneWayObjectMapper<NetUser, User> {
    override fun mapTo(a: NetUser): User {
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
}
