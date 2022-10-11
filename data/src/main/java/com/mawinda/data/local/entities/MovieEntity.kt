package com.mawinda.data.local.entities

import androidx.room.Dao
import androidx.room.Entity

@Entity(tableName = "movieTbl")
data class MovieEntity(
    val id: Int? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val genreIds: List<Int>? = null,
    val posterPath: String? = null,
    val originCountry: List<String>? = null,
    val backdropPath: String? = null,
    val mediaType: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val releaseDate: String? = null
)

@Dao
interface MovieDao {

}
