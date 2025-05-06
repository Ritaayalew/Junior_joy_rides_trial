package com.example.trial_junior.feature_junior.presentation.util

sealed class Screen(val route: String) {
    object InvitationItemListScreen: Screen("invitationItemList_screen")
    object InvitationNewUpdateScreen: Screen("invitationNewUpdate_screen")

    object ExampleScreen: Screen("example_screen")
    object WishListUpdateScreen: Screen("wishList_update_screen")

    object SignupScreen: Screen("signup_screen")
    object LoginScreen: Screen("login_screen")
    object ProfileScreen: Screen("profile_screen")
    object EditProfileScreen: Screen("edit_profile_screen")

}