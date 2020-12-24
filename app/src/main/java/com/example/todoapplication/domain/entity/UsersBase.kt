package com.example.todoapplication.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class UsersBase(@SerializedName("response") val response: List<User>)

@Parcelize
data class User(
    @SerializedName("f_name") val firstName: String,
    @SerializedName("l_name") val lastName: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("avatr_url") val avatrUrl: String,
    @SerializedName("specialty") val specialty: List<Specialty>
) : Parcelable

@Parcelize
data class Specialty(
    @SerializedName("specialty_id") val specialtyId: Int,
    @SerializedName("name") val name: String
) : Parcelable