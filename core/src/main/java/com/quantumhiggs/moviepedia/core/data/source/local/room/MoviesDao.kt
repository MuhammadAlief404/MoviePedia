package com.quantumhiggs.moviepedia.core.data.source.local.room

import androidx.room.*
import com.quantumhiggs.moviepedia.core.data.source.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies where is_favorite = 1")
    fun getAllMovieFavorites(): Flow<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuckMovies(movies: List<MoviesEntity>)

    @Update
    fun updateFavoriteMovie(movies: MoviesEntity)


}