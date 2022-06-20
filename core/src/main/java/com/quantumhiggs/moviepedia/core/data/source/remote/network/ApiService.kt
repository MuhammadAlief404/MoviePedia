package com.quantumhiggs.moviepedia.core.data.source.remote.network

import com.quantumhiggs.moviepedia.core.data.source.remote.response.MoviesResponse
import com.quantumhiggs.moviepedia.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getAllMovies(@Query("api_key") id: String): MoviesResponse

    @GET("tv/popular")
    suspend fun getAllTvShow(@Query("api_key") id: String): TvShowResponse
}