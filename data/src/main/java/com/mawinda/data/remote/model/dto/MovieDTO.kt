package com.mawinda.data.remote.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mawinda.data.local.entities.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDTO(

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int>? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("origin_country")
    val originCountry: List<String>? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("media_type")
    val mediaType: String? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("video")
    val video: Boolean? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null
) : Parcelable {
    fun toMovie(): Movie {
        return Movie(
            id,
            firstAirDate,
            overview,
            originalLanguage,
            genreIds,
            posterPath,
            originCountry,
            backdropPath,
            mediaType,
            originalName,
            popularity,
            voteAverage,
            name,
            adult,
            voteCount,
            originalTitle,
            video,
            title,
            releaseDate
        )
    }
}