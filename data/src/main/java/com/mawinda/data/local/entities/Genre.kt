package com.mawinda.data.local.entities

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "genre_tbl")
data class Genre(
    @PrimaryKey
    @SerializedName("genre_id")
    val id: Int,
    @SerializedName("genre_name")
    val name: String
) : Parcelable

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg genre: Genre)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg genre: Genre)

    @Delete
    fun delete(vararg genre: Genre)

    @Transaction
    @Query("SELECT * FROM genre_tbl WHERE id IN (:ids)")
    fun genres(vararg ids: Int): List<Genre>
}