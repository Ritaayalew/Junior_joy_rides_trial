//package com.example.trial_junior.feature_junior.domain.model
//
//data class InvitationItem(
//    val childName: String,
//    val guardianEmail: String,
//    val specialRequests: String,
//    val address: String,
//    val date: String,
//    val time: Long,
//    val upcoming: Boolean,
//    val guardianPhone: Long,
//    val age: Int,
//    val id: Int?=null   // Optional until set by the server
//
//)

package com.example.trial_junior.feature_junior.domain.model

interface ListItem {
    val id: Int?
    val upcoming: Boolean
}

data class InvitationItem(
    val childName: String,
    val guardianEmail: String,
    val specialRequests: String,
    val address: String,
    val date: String,
    val time: Long,
    override val upcoming: Boolean,
    val guardianPhone: Long,
    val age: Int,
    override val id: Int? = null
) : ListItem
