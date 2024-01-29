package com.example.shifttestexample.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user_info")
data class PersonInfo(
    @SerializedName("name")
    @Expose
    @Embedded
    val name: name,
    @Expose
    @SerializedName("location")
    @Embedded
    val location: location,
    @SerializedName("login")
    @Embedded
    val login: login,
    @SerializedName("dob")
    @Embedded
    val dob: dob,
    @SerializedName("picture")
    @Embedded
    val picture: picture,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("email")
    @Expose
    @PrimaryKey
    val email: String,
    @SerializedName("phone")
    val phone: String
) : Serializable
