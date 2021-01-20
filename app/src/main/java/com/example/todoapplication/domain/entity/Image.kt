package com.example.todoapplication.domain.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("id") var id: String,
    @SerializedName("author") var author: String,
    @SerializedName("width") var width: String,
    @SerializedName("height") var height: String,
    @SerializedName("url") var url: String,
    @SerializedName("download_url") var download_url: String
) : Parcelable