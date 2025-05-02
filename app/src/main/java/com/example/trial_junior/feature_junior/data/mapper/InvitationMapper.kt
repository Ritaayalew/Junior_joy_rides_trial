package com.example.trial_junior.feature_junior.data.mapper

import com.example.trial_junior.feature_junior.data.local.dto.LocalInvitationItem
import com.example.trial_junior.feature_junior.data.remote.dto.RemoteInvitationItem
import com.example.trial_junior.feature_junior.domain.model.InvitationItem

// InvitationItem to LocalInvitationItem: Requires id parameter to ensure non-null
fun InvitationItem.toLocalInvitationItem(id: Int): LocalInvitationItem {
    return LocalInvitationItem(
        childName = childName,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        address = address,
        date = date,
        time = time,
        upcoming = upcoming,
        guardianPhone = guardianPhone,
        age = age,
        id = id
    )
}

// InvitationItem to RemoteInvitationItem: Can use the nullable id directly
fun InvitationItem.toRemoteInvitationItem(): RemoteInvitationItem {
    return RemoteInvitationItem(
        childName = childName,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        address = address,
        date = date,
        time = time,
        upcoming = upcoming,
        guardianPhone = guardianPhone,
        age = age,
        id = id // Nullable, as RemoteInvitationItem expects Int?
    )
}

// LocalInvitationItem to InvitationItem: id is non-null, can be used directly
fun LocalInvitationItem.toInvitationItem(): InvitationItem {
    return InvitationItem(
        childName = childName,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        address = address,
        date = date,
        time = time,
        upcoming = upcoming,
        guardianPhone = guardianPhone,
        age = age,
        id = id // id is Int, compatible with Int?
    )
}

// LocalInvitationItem to RemoteInvitationItem: id is non-null, can be used directly
fun LocalInvitationItem.toRemoteInvitationItem(): RemoteInvitationItem {
    return RemoteInvitationItem(
        childName = childName,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        address = address,
        date = date,
        time = time,
        upcoming = upcoming,
        guardianPhone = guardianPhone,
        age = age,
        id = id // id is Int, compatible with Int?
    )
}

// RemoteInvitationItem to InvitationItem: id is nullable, can be used directly
fun RemoteInvitationItem.toInvitationItem(): InvitationItem {
    return InvitationItem(
        childName = childName,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        address = address,
        date = date,
        time = time,
        upcoming = upcoming,
        guardianPhone = guardianPhone,
        age = age,
        id = id // Both are Int?
    )
}

// RemoteInvitationItem to LocalInvitationItem: Use id directly with null-check
fun RemoteInvitationItem.toLocalInvitationItem(): LocalInvitationItem {
    val serverId = id ?: throw IllegalStateException("Remote invitation missing ID: $this")
    return LocalInvitationItem(
        childName = childName,
        guardianEmail = guardianEmail,
        specialRequests = specialRequests,
        address = address,
        date = date,
        time = time,
        upcoming = upcoming,
        guardianPhone = guardianPhone,
        age = age,
        id = serverId
    )
}

// List<InvitationItem> to List<LocalInvitationItem>: Requires ids to be passed
fun List<InvitationItem>.toLocalInvitationItemList(ids: List<Int>): List<LocalInvitationItem> {
    if (this.size != ids.size) {
        throw IllegalArgumentException("The number of items (${this.size}) must match the number of IDs (${ids.size})")
    }
    return this.mapIndexed { index, invitation ->
        LocalInvitationItem(
            childName = invitation.childName,
            guardianEmail = invitation.guardianEmail,
            specialRequests = invitation.specialRequests,
            address = invitation.address,
            date = invitation.date,
            time = invitation.time,
            upcoming = invitation.upcoming,
            guardianPhone = invitation.guardianPhone,
            age = invitation.age,
            id = ids[index]
        )
    }
}

// List<InvitationItem> to List<RemoteInvitationItem>: Can use nullable ids directly
fun List<InvitationItem>.toRemoteInvitationItemList(): List<RemoteInvitationItem> {
    return this.map { invitation ->
        RemoteInvitationItem(
            childName = invitation.childName,
            guardianEmail = invitation.guardianEmail,
            specialRequests = invitation.specialRequests,
            address = invitation.address,
            date = invitation.date,
            time = invitation.time,
            upcoming = invitation.upcoming,
            guardianPhone = invitation.guardianPhone,
            age = invitation.age,
            id = invitation.id
        )
    }
}

// године List<LocalInvitationItem> to List<InvitationItem>: ids are non-null
fun List<LocalInvitationItem>.toInvitationItemListFromLocal(): List<InvitationItem> {
    return this.map { invitation ->
        InvitationItem(
            childName = invitation.childName,
            guardianEmail = invitation.guardianEmail,
            specialRequests = invitation.specialRequests,
            address = invitation.address,
            date = invitation.date,
            time = invitation.time,
            upcoming = invitation.upcoming,
            guardianPhone = invitation.guardianPhone,
            age = invitation.age,
            id = invitation.id
        )
    }
}

// List<LocalInvitationItem> to List<RemoteInvitationItem>: ids are non-null
fun List<LocalInvitationItem>.toRemoteInvitationItemListFromLocal(): List<RemoteInvitationItem> {
    return this.map { invitation ->
        RemoteInvitationItem(
            childName = invitation.childName,
            guardianEmail = invitation.guardianEmail,
            specialRequests = invitation.specialRequests,
            address = invitation.address,
            date = invitation.date,
            time = invitation.time,
            upcoming = invitation.upcoming,
            guardianPhone = invitation.guardianPhone,
            age = invitation.age,
            id = invitation.id
        )
    }
}

// List<RemoteInvitationItem> to List<InvitationItem>: ids are nullable
fun List<RemoteInvitationItem>.toInvitationItemListFromRemote(): List<InvitationItem> {
    return this.map { invitation ->
        InvitationItem(
            childName = invitation.childName,
            guardianEmail = invitation.guardianEmail,
            specialRequests = invitation.specialRequests,
            address = invitation.address,
            date = invitation.date,
            time = invitation.time,
            upcoming = invitation.upcoming,
            guardianPhone = invitation.guardianPhone,
            age = invitation.age,
            id = invitation.id
        )
    }
}

// List<RemoteInvitationItem> to List<LocalInvitationItem>: Use id directly with null-check
fun List<RemoteInvitationItem>.toLocalInvitationItemListFromRemote(): List<LocalInvitationItem> {
    return this.map { invitation ->
        val serverId = invitation.id ?: throw IllegalStateException("Remote invitation missing ID: $invitation")
        LocalInvitationItem(
            childName = invitation.childName,
            guardianEmail = invitation.guardianEmail,
            specialRequests = invitation.specialRequests,
            address = invitation.address,
            date = invitation.date,
            time = invitation.time,
            upcoming = invitation.upcoming,
            guardianPhone = invitation.guardianPhone,
            age = invitation.age,
            id = serverId
        )
    }
}