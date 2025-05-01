package com.example.trial_junior.feature_junior.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trial_junior.feature_junior.data.local.dto.LocalBasicInterviewItem
import com.example.trial_junior.feature_junior.data.local.dto.LocalInvitationItem
import com.example.trial_junior.feature_junior.data.local.dto.LocalSpecialInterviewItem
import com.example.trial_junior.feature_junior.data.local.dto.LocalWishListItem

@Database(
    entities = [
        LocalInvitationItem::class,
        LocalBasicInterviewItem::class,
        LocalSpecialInterviewItem::class,
        LocalWishListItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class JuniorDatabase: RoomDatabase() {
    abstract val invitationDao:InvitationDao
    abstract val basicInterviewDao: BasicInterviewDao
    abstract val specialInterviewDao: SpecialInterviewDao
    abstract val wishListDao: WishListDao

    companion object{
        const val DATABASE_NAME = "junior_db"
    }
}