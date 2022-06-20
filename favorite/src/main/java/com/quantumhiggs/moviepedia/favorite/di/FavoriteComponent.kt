package com.quantumhiggs.moviepedia.favorite.di

import android.content.Context
import com.quantumhiggs.moviepedia.di.FavoriteModuleDependencies
import com.quantumhiggs.moviepedia.favorite.movie.FavoriteMovieFragment
import com.quantumhiggs.moviepedia.favorite.tvShow.FavoriteTvShowFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(favoriteMovieFragment: FavoriteMovieFragment)
    fun inject(favoriteTvShowFragment: FavoriteTvShowFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}