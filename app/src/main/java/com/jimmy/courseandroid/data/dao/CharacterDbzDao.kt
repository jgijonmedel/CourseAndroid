package com.jimmy.courseandroid.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jimmy.courseandroid.data.entity.CharacterDbzEntity

@Dao
interface CharacterDbzDao {

    @Query("SELECT * FROM character_dbz")
    fun getAll(): List<CharacterDbzEntity>

    @Query("SELECT * FROM character_dbz WHERE id == :characterId LIMIT 1")
    fun getById(characterId: String): CharacterDbzEntity

    @Insert
    fun insert(entity: CharacterDbzEntity)

    @Delete
    fun deleted(entity: CharacterDbzEntity)

}