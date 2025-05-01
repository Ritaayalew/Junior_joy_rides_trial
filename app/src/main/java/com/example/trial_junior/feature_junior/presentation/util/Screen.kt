package com.example.trial_junior.feature_junior.presentation.util

sealed class Screen(val route: String) {
    object InvitationItemListScreen: Screen("invitationItemList_screen")
    object InvitationNewUpdateScreen: Screen("invitationNewUpdate_screen")
}