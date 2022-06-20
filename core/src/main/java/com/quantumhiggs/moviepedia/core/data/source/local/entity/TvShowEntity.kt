package com.quantumhiggs.moviepedia.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShow")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var tvShowId: Int,

    @ColumnInfo(name = "tvShow_name")
    var tvShowName: String,

    @ColumnInfo(name = "tvShow_desc")
    var tvShowDesc: String,

    @ColumnInfo(name = "tvShow_image")
    var tvShowImage: String,

    @ColumnInfo(name = "tvShow_rating")
    var tvShowRating: String,

    @ColumnInfo(name = "tvShow_release")
    var tvShowReleaseDate: String,

    @ColumnInfo(name = "tvShow_language")
    var tvShowLanguage: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)