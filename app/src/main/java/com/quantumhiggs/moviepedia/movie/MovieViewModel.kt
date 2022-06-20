package com.quantumhiggs.moviepedia.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.quantumhiggs.moviepedia.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(movieUseCase: MovieUseCase): ViewModel() {
    val movieList = movieUseCase.getAllMovies().asLiveData()
}