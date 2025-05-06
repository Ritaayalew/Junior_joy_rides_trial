package com.example.trial_junior.feature_junior.presentation.viewModels.BasicInterview_Update

import com.example.trial_junior.feature_junior.domain.model.BasicInterviewItem

data class BasicInterviewNewUpdateState(
    val isChildNameHintVisible: Boolean = true,
    val isGuardianNameHintVisible: Boolean = true,
    val isGuardianPhoneHintVisible: Boolean = true,
    val isAgeHintVisible: Boolean = true,
    val isGuardianEmailHintVisible: Boolean = true,
    val isSpecialRequestsHintVisible: Boolean = true,
    val basicInterview: BasicInterviewItem = BasicInterviewItem(
        childName = "",
        guardianName = "",
        guardianPhone = 0L,
        age = 0,
        guardianEmail = "",
        specialRequests = "",
        upcoming = true,
        id = null
    ),
    val isLoading: Boolean = true,
    val error: String? = null
)