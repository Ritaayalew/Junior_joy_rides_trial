package com.example.trial_junior.feature_junior.presentation.invitation_list

import com.example.trial_junior.feature_junior.domain.model.InvitationItem
import com.example.trial_junior.feature_junior.domain.util.InvitationItemOrder
import com.example.trial_junior.feature_junior.domain.util.SortingDirection

data class InvitationListState(
    val items: List<InvitationItem> = emptyList(),
    val invitationItemOrder: InvitationItemOrder = InvitationItemOrder.Time(SortingDirection.Down, true),
    val isLoading: Boolean = true,
    val error: String? = null,
    val showHosted: Boolean = true
)