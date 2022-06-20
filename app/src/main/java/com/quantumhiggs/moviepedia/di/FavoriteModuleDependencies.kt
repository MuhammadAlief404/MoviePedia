package com.quantumhiggs.moviepedia.di

import com.quantumhiggs.moviepedia.core.domain.usecase.MovieUseCase
import com.quantumhiggs.moviepedia.core.domain.usecase.TvShowUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun movieUseCase(): MovieUseCase

    fun tvShowUseCase(): TvShowUseCase

}