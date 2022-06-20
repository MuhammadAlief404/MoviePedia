package com.quantumhiggs.moviepedia.movie.detail

import androidx.lifecycle.ViewModel
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavorite(movie: Movies, newStatus: Boolean) = movieUseCase.setFavoriteMovie(movie, newStatus)
}