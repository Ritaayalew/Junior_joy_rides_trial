package com.example.trial_junior.feature_junior.data.remote.dto

data class RemoteUserItem(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String,
    val invitations: List<RemoteInvitationItem>? = emptyList(),
    val basicInterviews: List<RemoteBasicInterviewItem>? = emptyList(),
    val specialInterviews: List<RemoteSpecialInterviewItem>? = emptyList(),
    val wishLists: List<RemoteWishListItem>? = emptyList()
)