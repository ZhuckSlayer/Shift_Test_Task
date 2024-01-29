package com.example.shifttestexample.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class dob(
    @SerializedName("date")
    val date:String,
    @SerializedName("age")
    val age:Int
): Serializable