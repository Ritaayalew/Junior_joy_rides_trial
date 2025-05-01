package com.example.trial_junior.feature_junior.data.repo

import android.util.Log
import com.example.trial_junior.feature_junior.data.di.IoDispatcher
import com.example.trial_junior.feature_junior.data.local.InvitationDao
import com.example.trial_junior.feature_junior.data.mapper.toInvitationItem
import com.example.trial_junior.feature_junior.data.mapper.toInvitationItemListFromLocal
import com.example.trial_junior.feature_junior.data.mapper.toLocalInvitationItem
import com.example.trial_junior.feature_junior.data.mapper.toLocalInvitationItemListFromRemote
import com.example.trial_junior.feature_junior.data.mapper.toRemoteInvitationItem
import com.example.trial_junior.feature_junior.data.remote.Api
import com.example.trial_junior.feature_junior.domain.model.InvitationItem
import com.example.trial_junior.feature_junior.domain.repo.InvitationListRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

class InvitationListRepoImpl(
    private val dao: InvitationDao,
    private val api: Api,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): InvitationListRepo {
    override suspend fun getAllInvitations(): List<InvitationItem> {
        getAllInvitationsFromRemote()
        return dao.getAllInvitationItems().toInvitationItemListFromLocal()
    }

    override suspend fun getAllInvitationsFromLocalCache(): List<InvitationItem> {
        return dao.getAllInvitationItems().toInvitationItemListFromLocal()
    }

    override suspend fun getAllInvitationsFromRemote() {
        return withContext(dispatcher) {
            try {
                // Sync remote data with Room cache
                refreshRoomCache()
                // Return data from Room cache
//                dao.getAllInvitationItems().toInvitationItemListFromLocal()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("HTTP", "Error: No data from Remote")
                        // Check if the local cache is empty
                        if (isCacheEmpty()) {
                            Log.e("Cache", "Error: No data from local Room cache")
                            throw Exception("Error: Device offline and no data from Room cache")
                        }
                        // If cache is not empty, return cached data
//                        dao.getAllInvitationItems().toInvitationItemListFromLocal()
                    }
                    else -> throw e // Re-throw other exceptions
                }
            }
        }
    }

    //additional helper functions for "getAllInvitationsFromRemote"
    private suspend fun refreshRoomCache(){
        val remoteInvitations = api.getAllInvitations().filterNotNull()
        dao.addAllInvitationItems(remoteInvitations.toLocalInvitationItemListFromRemote())
    }

    private fun isCacheEmpty(): Boolean {
        var empty=true
        if (dao.getAllInvitationItems().isNotEmpty()) empty=false
        return empty
    }
    //****



    override suspend fun getSingleInvitationItemById(id: Int): InvitationItem? {
        return dao.getSingleInvitationItemById(id)?.toInvitationItem()
    }

    override suspend fun addInvitationItem(invitation: InvitationItem) {
        // add our record in the remote db first
        val remoteResponse = api.addInvitation(invitation.toRemoteInvitationItem())
        if (remoteResponse.isSuccessful) {
            val remoteItem = remoteResponse.body()
            if (remoteItem != null) {
                // get the id assigned to the new record in our remote db
                val serverId = remoteItem.id ?: throw Exception("Server returned a null ID")
                val localItem = invitation.toLocalInvitationItem(serverId)
                // add the record to our local cache using the same id as our remote db
                dao.addInvitationItem(localItem)
            } else {
                throw Exception("Failed to get created item from server")
            }
        } else {
            throw Exception("Failed to create invitation on server: ${remoteResponse.message()}")
        }
    }

    override suspend fun updateInvitationItem(invitation: InvitationItem) {
        val invitationId = invitation.id ?: throw IllegalArgumentException("Cannot update an invitation without an ID")
        dao.addInvitationItem(invitation.toLocalInvitationItem(invitationId))
        api.updateInvitationItem(invitationId, invitation.toRemoteInvitationItem())
    }

    override suspend fun deleteInvitationItem(invitation: InvitationItem) {
        val invitationId = invitation.id ?: throw IllegalArgumentException("Cannot update an invitation without an ID")
        dao.deleteInvitationItem(invitation.toLocalInvitationItem(invitationId))
        try{
            val response= api.deleteInvitation(invitationId)
            if(response.isSuccessful){
                Log.i("API_DELETE","Response Success")
            }else{
                Log.i("API_DELETE", "Response Unsuccessful")
                Log.i("API_DELETE", response.message())
            }
        }catch (e: Exception){
            when(e) {
                is UnknownHostException, is ConnectException, is HttpException -> {
                    Log.e("HTTP", "Error: Could not delete")
                }else -> throw e
            }
        }
    }
}