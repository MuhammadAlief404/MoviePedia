package com.quantumhiggs.moviepedia.core.domain.usecase

import com.quantumhiggs.moviepedia.core.data.Resource
import com.quantumhiggs.moviepedia.core.domain.model.TvShows
import com.quantumhiggs.moviepedia.core.domain.repository.IMoviePediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowInteractor @Inject constructor(private val repository: IMoviePediaRepository): TvShowUseCase {
    override fun getAllTvShow(): Flow<Resource<List<TvShows>>> {
        return repository.getAllTvShow()
    }

    override fun getFavoriteTvShow(): Flow<List<TvShows>> {
        return repository.getTvShowFavorite()
    }

    override fun setFavoriteTvShow(tvShow: TvShows, state: Boolean) {
        return repository.setTvShowFavorite(tvShow, state)
    }
}