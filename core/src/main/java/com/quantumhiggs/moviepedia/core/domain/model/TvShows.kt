package com.quantumhiggs.moviepedia.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShows(
    val tvShowId: Int,
    val tvShowName: String,
    val tvShowDesc: String,
    val tvShowImage: String,
    val tvShowRating: String,
    val tvShowReleaseDate: String,
    val tvShowLanguage: String,
    val isFavorite: Boolean
):Parcelable