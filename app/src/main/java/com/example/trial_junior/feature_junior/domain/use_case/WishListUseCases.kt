package com.example.trial_junior.feature_junior.domain.use_case

import com.example.trial_junior.core.util.UseCasesStrings
import com.example.trial_junior.feature_junior.domain.model.WishListItem
import com.example.trial_junior.feature_junior.domain.repo.WishListRepo
import com.example.trial_junior.feature_junior.domain.util.InvalidInvitationItemException
import javax.inject.Inject

class WishListUseCases @Inject constructor(
    private val repo: WishListRepo
) {
    suspend fun addWishListItem(item: WishListItem) {
        if (item.childName.isBlank() || item.guardianEmail.isBlank() || item.age <= 0 ||
            item.date.isBlank() || item.specialRequests.isBlank() || item.imageUrl.isBlank()) {
            throw InvalidInvitationItemException(UseCasesStrings.EMPTY_FIELDS)
        }
        repo.addWishListItem(item)
    }

    suspend fun updateWishListItem(item: WishListItem) {
        if (item.childName.isBlank() || item.guardianEmail.isBlank() || item.age <= 0 ||
            item.date.isBlank() || item.specialRequests.isBlank() || item.imageUrl.isBlank()) {
            throw InvalidInvitationItemException(UseCasesStrings.EMPTY_FIELDS)
        }
        repo.updateWishListItem(item)
    }

    suspend fun deleteWishListItem(item: WishListItem) {
        repo.deleteWishListItem(item)
    }

    suspend fun toggleHostedWishListItem(item: WishListItem) {
        repo.updateWishListItem(item.copy(upcoming = !item.upcoming))
    }

    suspend fun getWishListItemById(id: Int): WishListItem? {
        return repo.getSingleWishListItemById(id)
    }

    suspend fun getWishListItems(
        showHosted: Boolean = true
    ): WishListUseCaseResult {
        var items = repo.getAllWishListItemsFromLocalCache()
        if (items.isEmpty()) {
            items = repo.getAllWishListItems()
        }

        val filteredItems = if (!showHosted) {
            items.filter { it.upcoming } // Show only upcoming items (upcoming = true)
        } else {
            items
        }

        return WishListUseCaseResult.Success(filteredItems)
    }
}

sealed class WishListUseCaseResult {
    data class Success(val items: List<WishListItem>) : WishListUseCaseResult()
    data class Error(val message: String) : WishListUseCaseResult()
}