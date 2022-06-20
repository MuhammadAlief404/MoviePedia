package com.quantumhiggs.moviepedia.tvShow.detail

import androidx.lifecycle.ViewModel
import com.quantumhiggs.moviepedia.core.domain.model.TvShows
import com.quantumhiggs.moviepedia.core.domain.usecase.TvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTvShowViewModel @Inject constructor(private val tvShowUseCase: TvShowUseCase): ViewModel() {
    fun setFavorite(tvShows: TvShows, newStatus: Boolean) = tvShowUseCase.setFavoriteTvShow(tvShows, newStatus)
}