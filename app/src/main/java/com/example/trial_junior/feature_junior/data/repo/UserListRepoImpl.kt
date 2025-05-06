package com.example.trial_junior.feature_junior.data.repo

import com.example.trial_junior.feature_junior.data.local.UserDao
import com.example.trial_junior.feature_junior.data.remote.Api
import com.example.trial_junior.feature_junior.data.TokenManager
import com.example.trial_junior.feature_junior.data.di.IoDispatcher
import com.example.trial_junior.feature_junior.data.mapper.toLocalUserItem
import com.example.trial_junior.feature_junior.data.mapper.toUserItem
import com.example.trial_junior.feature_junior.data.remote.dto.RegisterRequest
import com.example.trial_junior.feature_junior.domain.model.UserItem
import com.example.trial_junior.feature_junior.domain.repo.UserListRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserListRepoImpl @Inject constructor(
    private val dao: UserDao,
    private val api: Api,
    private val tokenManager: TokenManager,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserListRepo {

    override suspend fun registerUser(firstName: String, lastName: String, email: String, password: String): UserItem {
        return withContext(dispatcher) {
            val registerDTO = RegisterRequest(firstName, lastName, email, password)
            val response = api.registerUser(registerDTO)
            if (response.isSuccessful) {
                val responseBody = response.body() ?: throw Exception("Registration failed: No token received")
                val token = responseBody["token"] ?: throw Exception("Registration failed: Token not found in response")
                tokenManager.saveToken(token)
                // Fetch user profile after successful registration
                val userResponse = api.getMyProfile()
                if (userResponse != null) {
                    val user = userResponse.toUserItem()
                    dao.insertUser(user.toLocalUserItem(user.id ?: 0))
                    user
                } else {
                    throw Exception("Failed to fetch user profile after registration")
                }
            } else {
                throw Exception("Registration failed: ${response.message()}")
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): String {
        return withContext(dispatcher) {
            val loginDTO = mapOf("email" to email, "password" to password)
            val response = api.loginUser(loginDTO)
            if (response.isSuccessful) {
                val token = response.body()?.get("token") as? String ?: throw Exception("Login failed: No token received")
                tokenManager.saveToken(token)
                token
            } else {
                throw Exception("Login failed: ${response.message()}")
            }
        }
    }

    override suspend fun getMyProfile(): UserItem {
        return withContext(dispatcher) {
            val response = api.getMyProfile()
            if (response != null) {
                response.toUserItem().also { user ->
                    dao.insertUser(user.toLocalUserItem(user.id ?: 0))
                }
            } else {
                throw Exception("Failed to fetch profile")
            }
        }
    }

    override suspend fun updateProfile(email: String, firstName: String?, lastName: String?, newEmail: String?, password: String?): UserItem {
        return withContext(dispatcher) {
            val updateDTO = mutableMapOf<String, Any>()
            firstName?.let { updateDTO["firstName"] = it }
            lastName?.let { updateDTO["lastName"] = it }
            newEmail?.let { updateDTO["email"] = it }
            password?.let {
                updateDTO["password"] = it
                updateDTO["salt"] = "" // Server will generate salt
            }
            val response = api.updateProfile(updateDTO)
            if (response.isSuccessful) {
                val user = response.body()?.toUserItem() ?: throw Exception("Update failed")
                dao.insertUser(user.toLocalUserItem(user.id ?: 0))
                if (newEmail != null) {
                    tokenManager.clearToken()
                }
                user
            } else {
                throw Exception("Update failed: ${response.message()}")
            }
        }
    }
}