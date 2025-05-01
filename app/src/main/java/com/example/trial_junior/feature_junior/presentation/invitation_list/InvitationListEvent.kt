//package com.example.trial_junior.feature_junior.presentation.invitation_list
//
//import com.example.trial_junior.feature_junior.domain.model.InvitationItem
//import com.example.trial_junior.feature_junior.domain.util.InvitationItemOrder
//
//sealed class InvitationListEvent {
//    data class Sort(val invitationItemOrder: InvitationItemOrder): InvitationListEvent()
//    data class Delete(val invitation: InvitationItem): InvitationListEvent()
//    data class ToggleHosted(val invitation: InvitationItem): InvitationListEvent()
//    object UndoDelete: InvitationListEvent()
//}