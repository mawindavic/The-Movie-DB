package com.mawinda.data.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Error(
    @field:SerializedName("status_message")
    val statusMessage: String,
    @field:SerializedName("status_code")
    val statusCode: Int,
    @field:SerializedName("success")
    val success: Boolean
) : Parcelable
