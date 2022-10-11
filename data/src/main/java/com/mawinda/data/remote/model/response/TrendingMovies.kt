package com.mawinda.data.remote.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mawinda.data.remote.model.dto.MovieDTO
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrendingMovies(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<MovieDTO>,

    @field:SerializedName("total_results")
    val totalResults: Int
) : Parcelable {
    val isEndOfPagination: Boolean
        get() = page == totalPages
}

