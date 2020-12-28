package com.example.todoapplication.domain.entity

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("response") var response: List<EmploeeNetwork>)

data class EmploeeNetwork(
    @SerializedName("f_name") var fName: String,
    @SerializedName("l_name") var lName: String,
    @SerializedName("birthday") var birthday: String,
    @SerializedName("avatr_url") var avatrUrl: String,
    @SerializedName("specialty") var specialty: List<SpecialtyNetwork>
)

data class SpecialtyNetwork(
    @SerializedName("specialty_id") var specialtyId: Int,
    @SerializedName("name") var name: String
)