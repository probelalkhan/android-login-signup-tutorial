package net.simplifiedcoding.data.network

import net.simplifiedcoding.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse
}