package com.example.shifttestexample.pojo

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class location(
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state:String,
    @SerializedName("postcode")
    val postcode:String,
    @SerializedName("street")
    @Embedded
    val street: street,
    @SerializedName("coordinates")
    @Embedded
    val coordinates: coordinates
): Serializable

