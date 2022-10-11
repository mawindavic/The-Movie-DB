package com.mawinda.data.remote.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mawinda.data.remote.model.dto.MovieDTO
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrendingMovies(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieDTO?>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
) : Parcelable
