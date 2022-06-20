package com.quantumhiggs.moviepedia.core.data

import com.quantumhiggs.moviepedia.core.data.source.local.LocalDataSource
import com.quantumhiggs.moviepedia.core.data.source.remote.RemoteDataSource
import com.quantumhiggs.moviepedia.core.data.source.remote.network.ApiResponse
import com.quantumhiggs.moviepedia.core.data.source.remote.response.MoviesResult
import com.quantumhiggs.moviepedia.core.data.source.remote.response.TvShowResult
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.core.domain.model.TvShows
import com.quantumhiggs.moviepedia.core.domain.repository.IMoviePediaRepository
import com.quantumhiggs.moviepedia.core.util.AppExecutors
import com.quantumhiggs.moviepedia.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePediaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviePediaRepository {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesResult>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map { DataMapper.movieEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResult>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MoviesResult>) {
                val movieList = DataMapper.movieResponseToEntity(data)
                localDataSource.insertBuckMovies(movieList)
            }
        }.asFlow()

    override fun getMovieFavorite(): Flow<List<Movies>> {
        return localDataSource.getAllMovieFavorites().map { DataMapper.movieEntityToDomain(it) }
    }

    override fun setMovieFavorite(movies: Movies, state: Boolean) {
        val movieEntity = DataMapper.movieDomainToEntity(movies)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun getAllTvShow(): Flow<Resource<List<TvShows>>> =
        object : NetworkBoundResource<List<TvShows>, List<TvShowResult>>(appExecutors) {
            override fun loadFromDB(): Flow<List<TvShows>> {
                return localDataSource.getAllTvShows().map { DataMapper.tvShowEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<TvShows>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResult>>> =
                remoteDataSource.getAllTvShow()

            override suspend fun saveCallResult(data: List<TvShowResult>) {
                val tvShowList = DataMapper.tvShowResponseToEntity(data)
                localDataSource.insertBuckTvShow(tvShowList)
            }
        }.asFlow()

    override fun getTvShowFavorite(): Flow<List<TvShows>> {
        return localDataSource.getAllTvShowFavorites().map { DataMapper.tvShowEntityToDomain(it) }
    }

    override fun setTvShowFavorite(tvShow: TvShows, state: Boolean) {
        val tvShowEntity = DataMapper.tvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShowEntity, state) }
    }

}