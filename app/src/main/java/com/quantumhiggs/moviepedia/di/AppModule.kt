package com.quantumhiggs.moviepedia.di

import com.quantumhiggs.moviepedia.core.domain.usecase.MovieInteractor
import com.quantumhiggs.moviepedia.core.domain.usecase.MovieUseCase
import com.quantumhiggs.moviepedia.core.domain.usecase.TvShowInteractor
import com.quantumhiggs.moviepedia.core.domain.usecase.TvShowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    @Singleton
    abstract fun provideTvShowUseCase(tvShowInteractor: TvShowInteractor): TvShowUseCase
}