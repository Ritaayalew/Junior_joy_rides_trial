package com.example.trial_junior.feature_junior.data.mapper

import com.example.trial_junior.feature_junior.data.local.dto.LocalWishListItem
import com.example.trial_junior.feature_junior.data.remote.dto.RemoteWishListItem
import com.example.trial_junior.feature_junior.domain.model.WishListItem

fun WishListItem.toLocalWishListItem(id: Int): LocalWishListItem {
    return LocalWishListItem(
        childName = childName,
        guardianEmail = guardianEmail,
        age = age,
        date = date,
        specialRequests = specialRequests,
        imageUrl = imageUrl,
        upcoming = upcoming,
        id = id
    )
}

fun WishListItem.toRemoteWishListItem(): RemoteWishListItem {
    return RemoteWishListItem(
        childName = childName,
        guardianEmail = guardianEmail,
        age = age,
        date = date,
        specialRequests = specialRequests,
        imageUrl = imageUrl,
        upcoming = upcoming,
        id = id
    )
}

fun LocalWishListItem.toWishListItem(): WishListItem {
    return WishListItem(
        childName = childName,
        guardianEmail = guardianEmail,
        age = age,
        date = date,
        specialRequests = specialRequests,
        imageUrl = imageUrl,
        upcoming = upcoming,
        id = id
    )
}

fun LocalWishListItem.toRemoteWishListItem(): RemoteWishListItem {
    return RemoteWishListItem(
        childName = childName,
        guardianEmail = guardianEmail,
        age = age,
        date = date,
        specialRequests = specialRequests,
        imageUrl = imageUrl,
        upcoming = upcoming,
        id = id
    )
}

fun RemoteWishListItem.toWishListItem(): WishListItem {
    return WishListItem(
        childName = childName,
        guardianEmail = guardianEmail,
        age = age,
        date = date,
        specialRequests = specialRequests,
        imageUrl = imageUrl,
        upcoming = upcoming,
        id = id
    )
}

fun RemoteWishListItem.toLocalWishListItem(): LocalWishListItem {
    val serverId = id ?: throw IllegalStateException("Remote wish list item missing ID: $this")
    return LocalWishListItem(
        childName = childName,
        guardianEmail = guardianEmail,
        age = age,
        date = date,
        specialRequests = specialRequests,
        imageUrl = imageUrl,
        upcoming = upcoming,
        id = serverId
    )
}

fun List<WishListItem>.toLocalWishListItemList(ids: List<Int>): List<LocalWishListItem> {
    if (this.size != ids.size) {
        throw IllegalArgumentException("The number of items (${this.size}) must match the number of IDs (${ids.size})")
    }
    return this.mapIndexed { index, item ->
        LocalWishListItem(
            childName = item.childName,
            guardianEmail = item.guardianEmail,
            age = item.age,
            date = item.date,
            specialRequests = item.specialRequests,
            imageUrl = item.imageUrl,
            upcoming = item.upcoming,
            id = ids[index]
        )
    }
}

fun List<WishListItem>.toRemoteWishListItemList(): List<RemoteWishListItem> {
    return this.map { item ->
        RemoteWishListItem(
            childName = item.childName,
            guardianEmail = item.guardianEmail,
            age = item.age,
            date = item.date,
            specialRequests = item.specialRequests,
            imageUrl = item.imageUrl,
            upcoming = item.upcoming,
            id = item.id
        )
    }
}

fun List<LocalWishListItem>.toWishListItemListFromLocal(): List<WishListItem> {
    return this.map { item ->
        WishListItem(
            childName = item.childName,
            guardianEmail = item.guardianEmail,
            age = item.age,
            date = item.date,
            specialRequests = item.specialRequests,
            imageUrl = item.imageUrl,
            upcoming = item.upcoming,
            id = item.id
        )
    }
}

fun List<LocalWishListItem>.toRemoteWishListItemListFromLocal(): List<RemoteWishListItem> {
    return this.map { item ->
        RemoteWishListItem(
            childName = item.childName,
            guardianEmail = item.guardianEmail,
            age = item.age,
            date = item.date,
            specialRequests = item.specialRequests,
            imageUrl = item.imageUrl,
            upcoming = item.upcoming,
            id = item.id
        )
    }
}

fun List<RemoteWishListItem>.toWishListItemListFromRemote(): List<WishListItem> {
    return this.map { item ->
        WishListItem(
            childName = item.childName,
            guardianEmail = item.guardianEmail,
            age = item.age,
            date = item.date,
            specialRequests = item.specialRequests,
            imageUrl = item.imageUrl,
            upcoming = item.upcoming,
            id = item.id
        )
    }
}

fun List<RemoteWishListItem>.toLocalWishListItemListFromRemote(): List<LocalWishListItem> {
    return this.map { item ->
        val serverId = item.id ?: throw IllegalStateException("Remote wish list item missing ID: $item")
        LocalWishListItem(
            childName = item.childName,
            guardianEmail = item.guardianEmail,
            age = item.age,
            date = item.date,
            specialRequests = item.specialRequests,
            imageUrl = item.imageUrl,
            upcoming = item.upcoming,
            id = serverId
        )
    }
}