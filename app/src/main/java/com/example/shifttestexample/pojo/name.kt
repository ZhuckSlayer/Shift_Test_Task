package com.example.shifttestexample.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class name(
    @SerializedName("first")
    val first: String?=null,
    @SerializedName("last")
    val last: String?=null,
    @SerializedName("title")
    val title: String?=null,
): Serializable