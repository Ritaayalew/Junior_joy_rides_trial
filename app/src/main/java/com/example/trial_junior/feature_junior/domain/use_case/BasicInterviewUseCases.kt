package com.example.trial_junior.feature_junior.domain.use_case

import com.example.trial_junior.core.util.UseCasesStrings
import com.example.trial_junior.feature_junior.domain.model.BasicInterviewItem
import com.example.trial_junior.feature_junior.domain.repo.BasicInterviewListRepo
import com.example.trial_junior.feature_junior.domain.util.InvalidInvitationItemException
import com.example.trial_junior.feature_junior.domain.util.SortingDirection
import javax.inject.Inject

class BasicInterviewUseCases @Inject constructor(
    private val repo: BasicInterviewListRepo
) {
    suspend fun addBasicInterviewItem(item: BasicInterviewItem) {
        if (item.childName.isBlank() || item.guardianName.isBlank() || item.guardianPhone <= 0 ||
            item.age <= 0 || item.guardianEmail.isBlank() || item.specialRequests.isBlank()) {
            throw InvalidInvitationItemException(UseCasesStrings.EMPTY_FIELDS)
        }
        repo.addBasicInterviewItem(item)
    }

    suspend fun updateBasicInterviewItem(item: BasicInterviewItem) {
        if (item.childName.isBlank() || item.guardianName.isBlank() || item.guardianPhone <= 0 ||
            item.age <= 0 || item.guardianEmail.isBlank() || item.specialRequests.isBlank() ) {
            throw InvalidInvitationItemException(UseCasesStrings.EMPTY_FIELDS)
        }
        repo.updateBasicInterviewItem(item)
    }

    suspend fun deleteBasicInterviewItem(item: BasicInterviewItem) {
        repo.deleteBasicInterviewItem(item)
    }

    suspend fun toggleHostedBasicInterviewItem(item: BasicInterviewItem) {
        repo.updateBasicInterviewItem(item.copy(upcoming = !item.upcoming))
    }

    suspend fun getBasicInterviewItemById(id: Int): BasicInterviewItem? {
        return repo.getSingleBasicInterviewItemById(id)
    }

    suspend fun getBasicInterviewItems(
        showHosted: Boolean = true
    ): BasicInterviewUseCaseResult {
        var items = repo.getAllBasicInterviewsFromLocalCache()
        if (items.isEmpty()) {
            items = repo.getAllBasicInterviews()
        }

        val filteredItems = if (!showHosted) {
            items.filter { it.upcoming } // Show only upcoming items (upcoming = true)
        } else {
            items
        }

        return BasicInterviewUseCaseResult.Success(filteredItems)
    }
}

sealed class BasicInterviewUseCaseResult {
    data class Success(val items: List<BasicInterviewItem>) : BasicInterviewUseCaseResult()
    data class Error(val message: String) : BasicInterviewUseCaseResult()
}