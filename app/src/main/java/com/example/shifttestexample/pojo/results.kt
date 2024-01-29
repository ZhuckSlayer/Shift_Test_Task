package com.example.shifttestexample.pojo

import com.google.gson.annotations.SerializedName

data class results (
    @SerializedName("results")
    val results:List<PersonInfo>

)