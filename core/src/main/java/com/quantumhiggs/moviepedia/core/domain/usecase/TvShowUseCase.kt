package com.quantumhiggs.moviepedia.core.domain.usecase

import com.quantumhiggs.moviepedia.core.data.Resource
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.core.domain.model.TvShows
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getAllTvShow(): Flow<Resource<List<TvShows>>>
    fun getFavoriteTvShow() : Flow<List<TvShows>>
    fun setFavoriteTvShow(tvShow: TvShows, state: Boolean)
}