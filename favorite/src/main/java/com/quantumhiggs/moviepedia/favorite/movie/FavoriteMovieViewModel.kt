package com.quantumhiggs.moviepedia.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.quantumhiggs.moviepedia.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(movieUseCase: MovieUseCase) :
    ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovies().asLiveData()
}