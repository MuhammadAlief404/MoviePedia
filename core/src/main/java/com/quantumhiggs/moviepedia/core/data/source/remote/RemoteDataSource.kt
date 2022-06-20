package com.quantumhiggs.moviepedia.core.data.source.remote

import com.quantumhiggs.moviepedia.core.BuildConfig.API_KEY
import com.quantumhiggs.moviepedia.core.data.source.remote.network.ApiResponse
import com.quantumhiggs.moviepedia.core.data.source.remote.network.ApiService
import com.quantumhiggs.moviepedia.core.data.source.remote.response.MoviesResult
import com.quantumhiggs.moviepedia.core.data.source.remote.response.TvShowResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MoviesResult>>> {
        return flow {
            try {
                val response = apiService.getAllMovies(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<TvShowResult>>> {
        return flow {
            try {
                val response = apiService.getAllTvShow(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}