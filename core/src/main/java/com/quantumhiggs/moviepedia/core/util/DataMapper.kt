package com.quantumhiggs.moviepedia.core.util

import com.quantumhiggs.moviepedia.core.data.source.local.entity.MoviesEntity
import com.quantumhiggs.moviepedia.core.data.source.local.entity.TvShowEntity
import com.quantumhiggs.moviepedia.core.data.source.remote.response.MoviesResult
import com.quantumhiggs.moviepedia.core.data.source.remote.response.TvShowResult
import com.quantumhiggs.moviepedia.core.domain.model.Movies
import com.quantumhiggs.moviepedia.core.domain.model.TvShows

object DataMapper {

    fun movieEntityToDomain(input: List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                movieId = it.movieId,
                movieName = it.movieName,
                movieDesc = it.movieDesc,
                movieImage = it.movieImage,
                movieRating = it.movieRating,
                movieReleaseDate = it.movieReleaseDate,
                movieLanguage = it.movieLanguage,
                duration = it.duration,
                genre = it.genre,
                homepage = it.homepage,
                isFavorite = it.isFavorite
            )
        }

    fun movieResponseToEntity(input: List<MoviesResult>): List<MoviesEntity> {
        val movieList = arrayListOf<MoviesEntity>()
        input.map {
            val movie = MoviesEntity(
                movieId = it.id,
                movieName = it.title,
                movieDesc = it.overview,
                movieImage = it.poster_path,
                movieRating = it.vote_average.toString(),
                movieReleaseDate = it.release_date,
                movieLanguage = it.original_language
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun movieDomainToEntity(input: Movies) = MoviesEntity(
        movieId = input.movieId,
        movieName = input.movieName,
        movieDesc = input.movieDesc,
        movieImage = input.movieImage,
        movieRating = input.movieRating,
        movieReleaseDate = input.movieReleaseDate,
        movieLanguage = input.movieLanguage
    )

    fun tvShowEntityToDomain(input: List<TvShowEntity>): List<TvShows> =
        input.map {
            TvShows(
                tvShowId = it.tvShowId,
                tvShowName = it.tvShowName,
                tvShowDesc = it.tvShowDesc,
                tvShowImage = it.tvShowImage,
                tvShowRating = it.tvShowRating,
                tvShowReleaseDate = it.tvShowReleaseDate,
                tvShowLanguage = it.tvShowLanguage,
                isFavorite = it.isFavorite
            )
        }

    fun tvShowResponseToEntity(input: List<TvShowResult>): List<TvShowEntity> {
        val tvShowList = arrayListOf<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                tvShowId = it.id,
                tvShowName = it.name,
                tvShowDesc = it.overview,
                tvShowImage = it.posterPath ?: "",
                tvShowRating = it.voteAverage.toString(),
                tvShowReleaseDate = it.firstAirDate,
                tvShowLanguage = it.originalLanguage,
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun tvShowDomainToEntity(input: TvShows) = TvShowEntity(
        tvShowId = input.tvShowId,
        tvShowName = input.tvShowName,
        tvShowDesc = input.tvShowDesc,
        tvShowImage = input.tvShowImage,
        tvShowRating = input.tvShowRating,
        tvShowReleaseDate = input.tvShowReleaseDate,
        tvShowLanguage = input.tvShowLanguage,
    )
}