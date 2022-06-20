package com.quantumhiggs.moviepedia.core.domain.usecase

import com.quantumhiggs.moviepedia.core.data.Resource
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovies() : Flow<List<Movies>>
    fun setFavoriteMovie(movies: Movies, state: Boolean)
}