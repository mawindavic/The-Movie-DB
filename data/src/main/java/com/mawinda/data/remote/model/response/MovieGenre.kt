package com.mawinda.data.remote.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mawinda.data.remote.model.dto.GenreDTO
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieGenre(
    @field:SerializedName("genres")
    val genres: List<GenreDTO>
) : Parcelable

