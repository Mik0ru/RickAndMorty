package com.rickandmorty.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    val characterId: Int,
    val image: String,
    val name: String,
    val status: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}