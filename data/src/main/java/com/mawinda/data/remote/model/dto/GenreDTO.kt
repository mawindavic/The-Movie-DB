package com.mawinda.data.remote.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mawinda.data.local.entities.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreDTO(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("id")
    val id: Int
) : Parcelable {
    fun toGenre(): Genre = Genre(id, name)
}