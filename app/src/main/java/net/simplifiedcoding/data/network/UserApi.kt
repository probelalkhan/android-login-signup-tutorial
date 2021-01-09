package net.simplifiedcoding.data.network

import net.simplifiedcoding.data.responses.LoginResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi : BaseApi{
    @GET("user")
    suspend fun getUser(): LoginResponse
}