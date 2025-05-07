package com.example.trial_junior.feature_junior.domain.model

data class UserItem(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String,
    val invitations: List<InvitationItem>? = emptyList(),
    val basicInterviews: List<BasicInterviewItem>? = emptyList(),
    val specialInterviews: List<SpecialInterviewItem>? = emptyList(),
    val wishLists: List<WishListItem>? = emptyList()
)