package com.quantumhiggs.moviepedia.favorite.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.quantumhiggs.moviepedia.core.domain.usecase.TvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteTvShowViewModel @Inject constructor(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val favoriteTvShow = tvShowUseCase.getFavoriteTvShow().asLiveData()
}