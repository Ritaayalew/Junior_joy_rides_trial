package com.example.trial_junior.feature_junior.data.mapper

import com.example.trial_junior.feature_junior.data.local.dto.LocalBasicInterviewItem
import com.example.trial_junior.feature_junior.data.remote.dto.RemoteBasicInterviewItem
import com.example.trial_junior.feature_junior.domain.model.BasicInterviewItem


fun BasicInterviewItem.toLocalBasicInterviewItem(id: Int): LocalBasicInterviewItem {
    return LocalBasicInterviewItem(
        childName = childName,
        guardianName = guardianName,
        guardianPhone = guardianPhone,
        age = age,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        upcoming = upcoming,
        id = id
    )
}

fun BasicInterviewItem.toRemoteBasicInterviewItem(): RemoteBasicInterviewItem {
    return RemoteBasicInterviewItem(
        childName = childName,
        guardianName = guardianName,
        guardianPhone = guardianPhone,
        age = age,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        upcoming = upcoming,
        id = id
    )
}

fun LocalBasicInterviewItem.toBasicInterviewItem(): BasicInterviewItem {
    return BasicInterviewItem(
        childName = childName,
        guardianName = guardianName,
        guardianPhone = guardianPhone,
        age = age,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        upcoming = upcoming,
        id = id
    )
}

fun LocalBasicInterviewItem.toRemoteBasicInterviewItem(): RemoteBasicInterviewItem {
    return RemoteBasicInterviewItem(
        childName = childName,
        guardianName = guardianName,
        guardianPhone = guardianPhone,
        age = age,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        upcoming = upcoming,
        id = id
    )
}

fun RemoteBasicInterviewItem.toBasicInterviewItem(): BasicInterviewItem {
    return BasicInterviewItem(
        childName = childName,
        guardianName = guardianName,
        guardianPhone = guardianPhone,
        age = age,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        upcoming = upcoming,
        id = id
    )
}

fun RemoteBasicInterviewItem.toLocalBasicInterviewItem(): LocalBasicInterviewItem {
    val serverId = id ?: throw IllegalStateException("Remote basic interview missing ID: $this")
    return LocalBasicInterviewItem(
        childName = childName,
        guardianName = guardianName,
        guardianPhone = guardianPhone,
        age = age,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        upcoming = upcoming,
        id = serverId
    )
}

fun List<BasicInterviewItem>.toLocalBasicInterviewItemList(ids: List<Int>): List<LocalBasicInterviewItem> {
    if (this.size != ids.size) {
        throw IllegalArgumentException("The number of items (${this.size}) must match the number of IDs (${ids.size})")
    }
    return this.mapIndexed { index, item ->
        LocalBasicInterviewItem(
            childName = item.childName,
            guardianName = item.guardianName,
            guardianPhone = item.guardianPhone,
            age = item.age,
            guardianEmail = item.guardianEmail,
            specialRequests = item.specialRequests,
            upcoming = item.upcoming,
            id = ids[index]
        )
    }
}

fun List<BasicInterviewItem>.toRemoteBasicInterviewItemList(): List<RemoteBasicInterviewItem> {
    return this.map { item ->
        RemoteBasicInterviewItem(
            childName = item.childName,
            guardianName = item.guardianName,
            guardianPhone = item.guardianPhone,
            age = item.age,
            guardianEmail = item.guardianEmail,
            specialRequests = item.specialRequests,
            upcoming = item.upcoming,
            id = item.id
        )
    }
}

fun List<LocalBasicInterviewItem>.toBasicInterviewItemListFromLocal(): List<BasicInterviewItem> {
    return this.map { item ->
        BasicInterviewItem(
            childName = item.childName,
            guardianName = item.guardianName,
            guardianPhone = item.guardianPhone,
            age = item.age,
            guardianEmail = item.guardianEmail,
            specialRequests = item.specialRequests,
            upcoming = item.upcoming,
            id = item.id
        )
    }
}

fun List<LocalBasicInterviewItem>.toRemoteBasicInterviewItemListFromLocal(): List<RemoteBasicInterviewItem> {
    return this.map { item ->
        RemoteBasicInterviewItem(
            childName = item.childName,
            guardianName = item.guardianName,
            guardianPhone = item.guardianPhone,
            age = item.age,
            guardianEmail = item.guardianEmail,
            specialRequests = item.specialRequests,
            upcoming = item.upcoming,
            id = item.id
        )
    }
}

fun List<RemoteBasicInterviewItem>.toBasicInterviewItemListFromRemote(): List<BasicInterviewItem> {
    return this.map { item ->
        BasicInterviewItem(
            childName = item.childName,
            guardianName = item.guardianName,
            guardianPhone = item.guardianPhone,
            age = item.age,
            guardianEmail = item.guardianEmail,
            specialRequests = item.specialRequests,
            upcoming = item.upcoming,
            id = item.id
        )
    }
}

fun List<RemoteBasicInterviewItem>.toLocalBasicInterviewItemListFromRemote(): List<LocalBasicInterviewItem> {
    return this.map { item ->
        val serverId = item.id ?: throw IllegalStateException("Remote basic interview missing ID: $item")
        LocalBasicInterviewItem(
            childName = item.childName,
            guardianName = item.guardianName,
            guardianPhone = item.guardianPhone,
            age = item.age,
            guardianEmail = item.guardianEmail,
            specialRequests = item.specialRequests,
            upcoming = item.upcoming,
            id = serverId
        )
    }
}