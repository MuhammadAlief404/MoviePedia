package com.quantumhiggs.moviepedia.core.domain.repository

import com.quantumhiggs.moviepedia.core.data.Resource
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.core.domain.model.TvShows
import kotlinx.coroutines.flow.Flow

interface IMoviePediaRepository {

    fun getAllMovies(): Flow<Resource<List<Movies>>>
    fun getMovieFavorite(): Flow<List<Movies>>
    fun setMovieFavorite(movies: Movies, state: Boolean)

    fun getAllTvShow(): Flow<Resource<List<TvShows>>>
    fun getTvShowFavorite(): Flow<List<TvShows>>
    fun setTvShowFavorite(tvShow: TvShows, state: Boolean)

}