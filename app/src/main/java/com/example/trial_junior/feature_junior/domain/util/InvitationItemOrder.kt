package com.example.trial_junior.feature_junior.domain.util

sealed class InvitationItemOrder(
    val sortingDirection: SortingDirection,
    val showHosted: Boolean
) {
    class Time(sortingDirection: SortingDirection, showHosted: Boolean): InvitationItemOrder(sortingDirection,showHosted)

    fun copy( sortingDirection: SortingDirection, showHosted: Boolean): InvitationItemOrder{
        return when(this){
            is Time -> Time(sortingDirection, showHosted)
        }
    }

}