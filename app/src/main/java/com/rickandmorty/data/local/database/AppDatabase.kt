package com.rickandmorty.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rickandmorty.data.local.database.dao.FavoritesDao
import com.rickandmorty.data.local.models.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

}