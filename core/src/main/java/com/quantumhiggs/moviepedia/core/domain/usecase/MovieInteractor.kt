package com.quantumhiggs.moviepedia.core.domain.usecase

import com.quantumhiggs.moviepedia.core.data.Resource
import com.quantumhiggs.moviepedia.core.data.source.remote.response.MoviesResult
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.core.domain.repository.IMoviePediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val repository: IMoviePediaRepository): MovieUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> {
        return repository.getAllMovies()
    }

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return repository.getMovieFavorite()
    }

    override fun setFavoriteMovie(movies: Movies, state: Boolean) {
        return repository.setMovieFavorite(movies, state)
    }

}