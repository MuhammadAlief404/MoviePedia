package com.quantumhiggs.moviepedia.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    val movieId: Int,
    val movieName: String,
    val movieDesc: String,
    val movieImage: String,
    val movieRating: String,
    val movieReleaseDate: String,
    val movieLanguage: String,
    val duration: String,
    val genre: String,
    val homepage: String,
    val isFavorite: Boolean
): Parcelable