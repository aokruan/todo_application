package com.example.todoapplication.domain.entity

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("response") var response : List<UserNet>
)

data class UserNet (
    @SerializedName("f_name") var fName : String,
    @SerializedName("l_name") var lName : String,
    @SerializedName("birthday") var birthday : String,
    @SerializedName("avatr_url") var avatrUrl : String,
    @SerializedName("specialty") var specialty : List<Specialty1>
)

data class Specialty1 (
    @SerializedName("specialty_id") var specialtyId : Int,
    @SerializedName("name") var name : String
)