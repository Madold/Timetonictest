package com.markusw.timetonictest.network.data.remote.responses

import com.google.gson.annotations.SerializedName

data class SessionKeyResponse(
    val status: String,
    @SerializedName("sesskey") val sessionKey: String,
)
