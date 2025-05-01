package com.example.trial_junior.feature_junior.domain.use_case

import com.example.trial_junior.core.util.UseCasesStrings
import com.example.trial_junior.feature_junior.domain.model.SpecialInterviewItem
import com.example.trial_junior.feature_junior.domain.repo.SpecialInterviewListRepo
import com.example.trial_junior.feature_junior.domain.util.InvalidInvitationItemException
import javax.inject.Inject

class SpecialInterviewUseCases @Inject constructor(
    private val repo: SpecialInterviewListRepo
) {
    suspend fun addSpecialInterviewItem(item: SpecialInterviewItem) {
        if (item.childName.isBlank() || item.guardianName.isBlank() || item.guardianPhone <= 0 ||
            item.age <= 0 || item.guardianEmail.isBlank() || item.specialRequests.isBlank() ||
            item.videoUrl.isBlank()) {
            throw InvalidInvitationItemException(UseCasesStrings.EMPTY_FIELDS)
        }
        repo.addSpecialInterviewItem(item)
    }

    suspend fun updateSpecialInterviewItem(item: SpecialInterviewItem) {
        if (item.childName.isBlank() || item.guardianName.isBlank() || item.guardianPhone <= 0 ||
            item.age <= 0 || item.guardianEmail.isBlank() || item.specialRequests.isBlank() ||
            item.videoUrl.isBlank()) {
            throw InvalidInvitationItemException(UseCasesStrings.EMPTY_FIELDS)
        }
        repo.updateSpecialInterviewItem(item)
    }

    suspend fun deleteSpecialInterviewItem(item: SpecialInterviewItem) {
        repo.deleteSpecialInterviewItem(item)
    }

    suspend fun toggleHostedSpecialInterviewItem(item: SpecialInterviewItem) {
        repo.updateSpecialInterviewItem(item.copy(upcoming = !item.upcoming))
    }

    suspend fun getSpecialInterviewItemById(id: Int): SpecialInterviewItem? {
        return repo.getSingleSpecialInterviewItemById(id)
    }

    suspend fun getSpecialInterviewItems(
        showHosted: Boolean = true
    ): SpecialInterviewUseCaseResult {
        var items = repo.getAllSpecialInterviewsFromLocalCache()
        if (items.isEmpty()) {
            items = repo.getAllSpecialInterviews()
        }

        val filteredItems = if (!showHosted) {
            items.filter { it.upcoming } // Show only upcoming items (upcoming = true)
        } else {
            items
        }

        return SpecialInterviewUseCaseResult.Success(filteredItems)
    }
}

sealed class SpecialInterviewUseCaseResult {
    data class Success(val items: List<SpecialInterviewItem>) : SpecialInterviewUseCaseResult()
    data class Error(val message: String) : SpecialInterviewUseCaseResult()
}