package com.rickandmorty.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rickandmorty.data.local.models.CharacterEntity

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: CharacterEntity)

    @Query("DELETE FROM character WHERE characterId = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM character")
    suspend fun getAll(): List<CharacterEntity>
}