package net.simplifiedcoding.data.responses

data class TokenResponse(
    val access_token: String?,
    val refresh_token: String?
)