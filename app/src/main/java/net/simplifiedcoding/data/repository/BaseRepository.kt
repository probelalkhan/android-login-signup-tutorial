package net.simplifiedcoding.data.repository

import net.simplifiedcoding.data.network.BaseApi
import net.simplifiedcoding.data.network.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall {
        api.logout()
    }
}