package com.quantumhiggs.moviepedia.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quantumhiggs.moviepedia.core.data.source.local.entity.MoviesEntity
import com.quantumhiggs.moviepedia.core.data.source.local.entity.TvShowEntity

@Database(entities = [MoviesEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MoviePediaDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowDao(): TvShowDao
}