package com.example.trial_junior.feature_junior.data.remote.dto

data class RemoteInvitationItem(
    val childName: String,
    val guardianEmail: String,
    val specialRequests: String,
    val address: String,
    val date: String,
    val time: Long,
    val upcoming: Boolean,
    val guardianPhone: Long,
    val age: Int,
    val id: Int?=null    // Optional for POST, present in response since the remote db (mysql) auto generates it

)
