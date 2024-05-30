package com.markusw.timetonictest.network.data.remote.responses

data class OauthKeyResponse(
    val status: String,
    val oauthkey: String,
    val o_u: String,
)