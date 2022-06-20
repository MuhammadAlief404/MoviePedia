package com.quantumhiggs.moviepedia.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.quantumhiggs.moviepedia.core.domain.usecase.TvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val tvShowList = tvShowUseCase.getAllTvShow().asLiveData()
}