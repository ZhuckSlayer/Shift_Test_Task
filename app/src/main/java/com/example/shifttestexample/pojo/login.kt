package com.example.shifttestexample.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class login (
    @SerializedName("username")
    val username:String,
): Serializable
