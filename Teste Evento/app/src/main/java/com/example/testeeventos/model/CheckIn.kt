package com.example.testeeventos.model

import com.google.gson.annotations.SerializedName

data class CheckIn(
    @SerializedName("eventId")
    val eventId: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?
)