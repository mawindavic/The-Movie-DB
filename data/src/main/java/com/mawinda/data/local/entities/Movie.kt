package com.mawinda.data.local.entities

import androidx.paging.PagingSource
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "movieTbl")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val id: Int,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null,
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>? = null,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "origin_country")
    val originCountry: List<String>? = null,
    @ColumnInfo(name = "back_drop_path")
    val backdropPath: String? = null,
    @ColumnInfo(name = "media_type")
    val mediaType: String? = null,
    @ColumnInfo(name = "original_name")
    val originalName: String? = null,
    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "is_adult")
    val adult: Boolean? = null,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int? = null,
    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,
    @ColumnInfo(name = "is_video")
    val video: Boolean? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null
)

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieTbl")
    fun movies(): PagingSource<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movie: Movie)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg movie: Movie)

    @Delete
    fun delete(vararg movie: Movie)


}
