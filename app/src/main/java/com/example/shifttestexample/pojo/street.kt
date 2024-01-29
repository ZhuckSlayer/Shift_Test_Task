package com.example.shifttestexample.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class street(
    @SerializedName("name")
    val name:String,
    @SerializedName("number")
    val number:String
): Serializable