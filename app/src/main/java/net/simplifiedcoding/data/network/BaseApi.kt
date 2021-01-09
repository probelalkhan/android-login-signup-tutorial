package net.simplifiedcoding.data.network

import okhttp3.ResponseBody
import retrofit2.http.POST

interface BaseApi {
    @POST("logout")
    suspend fun logout(): ResponseBody
}