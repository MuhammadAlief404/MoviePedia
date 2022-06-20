package com.quantumhiggs.moviepedia.core.di

import com.quantumhiggs.moviepedia.core.data.MoviePediaRepository
import com.quantumhiggs.moviepedia.core.domain.repository.IMoviePediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: MoviePediaRepository): IMoviePediaRepository
}