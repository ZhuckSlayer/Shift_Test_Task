package com.example.shifttestexample.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class picture (
    @SerializedName("large")
    val large:String
): Serializable