package com.example.trial_junior.feature_junior.presentation.invitation_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.trial_junior.core.util.ListStrings
import com.example.trial_junior.feature_junior.data.di.IoDispatcher
import com.example.trial_junior.feature_junior.domain.model.InvitationItem
import com.example.trial_junior.feature_junior.domain.use_case.InvitationUseCaseResult
import com.example.trial_junior.feature_junior.domain.use_case.InvitationUseCases
import com.example.trial_junior.feature_junior.domain.util.InvitationItemOrder
import com.example.trial_junior.feature_junior.domain.util.SortingDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class InvitationListViewModel @Inject constructor(
    private val invitationUseCases: InvitationUseCases,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : ListViewModel<InvitationItem>(dispatcher) {

    private val _invitationItemOrder = mutableStateOf<InvitationItemOrder>(
        InvitationItemOrder.Time(SortingDirection.Down, showHosted = true)
    )
    val invitationItemOrder: State<InvitationItemOrder> = _invitationItemOrder

    override val state: State<ListState<InvitationItem>> = _state

    private var recentlyDeletedItem: InvitationItem? = null

    override suspend fun fetchItems(): ListUseCaseResult<InvitationItem> {
        return try {
            when (val result = invitationUseCases.getInvitationItems(
                invitationItemOrder = _invitationItemOrder.value
            )) {
                is InvitationUseCaseResult.Success -> ListUseCaseResult.Success(result.invitationItems)
                is InvitationUseCaseResult.Error -> ListUseCaseResult.Error(result.message)
            }
        } catch (e: Exception) {
            ListUseCaseResult.Error("${ListStrings.CANT_GET_INVITATIONS}: ${e.message}")
        }
    }

    override suspend fun deleteItem(item: InvitationItem) {
        invitationUseCases.deleteInvitationItem(item)
    }

    override suspend fun restoreItem(item: InvitationItem) {
        invitationUseCases.addInvitationItem(item)
    }

    override suspend fun toggleHosted(item: InvitationItem) {
        invitationUseCases.toggleHostedInvitationItem(item)
    }

    fun onEvent(event: InvitationListEvent) {
        when (event) {
            is InvitationListEvent.Sort -> {
                val stateOrderAlreadyMatchesEventOrder =
                    _invitationItemOrder.value.sortingDirection == event.invitationItemOrder.sortingDirection &&
                            _invitationItemOrder.value.showHosted == event.invitationItemOrder.showHosted
                if (stateOrderAlreadyMatchesEventOrder) {
                    return
                }
                _invitationItemOrder.value = event.invitationItemOrder
                getItems()
            }
            is InvitationListEvent.Delete -> {
                recentlyDeletedItem = event.invitation
                super.onEvent(ListEvent.DeleteItem(event.invitation))
            }
            is InvitationListEvent.UndoDelete -> {
                recentlyDeletedItem?.let { item ->
                    super.onEvent(ListEvent.RestoreItem(item))
                    recentlyDeletedItem = null
                }
            }
            is InvitationListEvent.ToggleHosted -> {
                super.onEvent(ListEvent.ToggleHosted(event.invitation))
            }
        }
    }
}

sealed class InvitationListEvent {
    data class Sort(val invitationItemOrder: InvitationItemOrder) : InvitationListEvent()
    data class Delete(val invitation: InvitationItem) : InvitationListEvent()
    data class UndoDelete(val invitation: InvitationItem) : InvitationListEvent()
    data class ToggleHosted(val invitation: InvitationItem) : InvitationListEvent()
}

//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.trial_junior.core.util.ListStrings
//import com.example.trial_junior.feature_junior.data.di.IoDispatcher
//import com.example.trial_junior.feature_junior.domain.model.InvitationItem
//import com.example.trial_junior.feature_junior.domain.use_case.InvitationUseCaseResult
//import com.example.trial_junior.feature_junior.domain.use_case.InvitationUseCases
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class InvitationListViewModel @Inject constructor(
//    private val invitationUseCases: InvitationUseCases,
//    @IoDispatcher private val dispatcher: CoroutineDispatcher
//): ViewModel() {
//
//    private val _state = mutableStateOf(InvitationListState())
//    val state: State<InvitationListState> = _state
//    private var undoInvitationItem: InvitationItem? = null
//    private var getInvitationItemsJob: Job? = null
//    private val errorHandler = CoroutineExceptionHandler { _, e ->
//        e.printStackTrace()
//        _state.value = _state.value.copy(
//            error = e.message,
//            isLoading = false
//        )
//    }
//
//
//    fun onEvent(event: InvitationListEvent){
//        when(event){
//            is InvitationListEvent.Delete -> {
//                viewModelScope.launch (dispatcher + errorHandler){
//                    invitationUseCases.deleteInvitationItem(event.invitation)
//                    getInvitationItems()
//                    undoInvitationItem = event.invitation
//                }
//            }
//            is InvitationListEvent.Sort -> {
//                val stateOrderAlreadyMatchesEventOrder =
//                    _state.value.invitationItemOrder::class == event.invitationItemOrder::class &&
//                    _state.value.invitationItemOrder.showHosted == event.invitationItemOrder.showHosted &&
//                    _state.value.invitationItemOrder.sortingDirection == event.invitationItemOrder.sortingDirection
//                if(stateOrderAlreadyMatchesEventOrder){
//                    return
//                }
//
//                _state.value = _state.value.copy(
//                    invitationItemOrder = event.invitationItemOrder
//                )
//                getInvitationItems()
//            }
//            is InvitationListEvent.ToggleUpcoming -> {
//                viewModelScope.launch (dispatcher + errorHandler){
//                    invitationUseCases.toggleUpcomingInvitationItem(invitation = event.invitation)
//                    getInvitationItems()
//                }
//            }
//            InvitationListEvent.UndoDelete -> {
//                viewModelScope.launch (dispatcher + errorHandler){
//                    invitationUseCases.addInvitationItem(undoInvitationItem ?: return@launch)
//                    undoInvitationItem = null
//                    getInvitationItems()
//                }
//            }
//        }
//
//    }
//
//
//    fun getInvitationItems(){
//        getInvitationItemsJob?.cancel()
//
//        getInvitationItemsJob = viewModelScope.launch(dispatcher + errorHandler) {
//            val result = invitationUseCases.getInvitationItems(
//                invitationItemOrder = _state.value.invitationItemOrder
//            )
//            when(result){
//                is InvitationUseCaseResult.Success -> {
//                    _state.value = _state.value.copy(
//                        items = result.invitationItems,
//                        invitationItemOrder = _state.value.invitationItemOrder,
//                        isLoading = false
//                    )
//                }
//                is InvitationUseCaseResult.Error -> {
//                    _state.value = _state.value.copy(
//                        error = ListStrings.CANT_GET_INVITATIONS,
//                        isLoading = false
//                    )
//                }
//            }
//        }
//    }
//
//
//
//}