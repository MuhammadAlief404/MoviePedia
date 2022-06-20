package com.quantumhiggs.moviepedia.core.data.source.local

import com.quantumhiggs.moviepedia.core.data.source.local.entity.MoviesEntity
import com.quantumhiggs.moviepedia.core.data.source.local.entity.TvShowEntity
import com.quantumhiggs.moviepedia.core.data.source.local.room.MoviesDao
import com.quantumhiggs.moviepedia.core.data.source.local.room.TvShowDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val moviesDao: MoviesDao,
    private val tvShowDao: TvShowDao
) {

    fun getAllMovies(): Flow<List<MoviesEntity>> = moviesDao.getAllMovies()

    fun getAllMovieFavorites(): Flow<List<MoviesEntity>> = moviesDao.getAllMovieFavorites()

    fun setFavoriteMovie(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorite = newState
        moviesDao.updateFavoriteMovie(movies)
    }

    suspend fun insertBuckMovies(movies: List<MoviesEntity>) = moviesDao.insertBuckMovies(movies)

    fun getAllTvShows(): Flow<List<TvShowEntity>> = tvShowDao.getAllTvShows()

    fun getAllTvShowFavorites(): Flow<List<TvShowEntity>> = tvShowDao.getAllTvShowFavorites()

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        tvShowDao.updateFavoriteTvShow(tvShow)
    }

    suspend fun insertBuckTvShow(tvShows: List<TvShowEntity>) = tvShowDao.insertBuckTvShow(tvShows)

}