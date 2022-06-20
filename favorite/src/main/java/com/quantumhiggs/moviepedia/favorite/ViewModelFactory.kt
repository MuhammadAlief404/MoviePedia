package com.quantumhiggs.moviepedia.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.quantumhiggs.moviepedia.core.domain.usecase.MovieUseCase
import com.quantumhiggs.moviepedia.core.domain.usecase.TvShowUseCase
import com.quantumhiggs.moviepedia.favorite.movie.FavoriteMovieViewModel
import com.quantumhiggs.moviepedia.favorite.tvShow.FavoriteTvShowViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                FavoriteTvShowViewModel(tvShowUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}