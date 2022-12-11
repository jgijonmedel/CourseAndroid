package com.jimmy.courseandroid.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_dbz")
data class CharacterDbzEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "race") val race: String,
    @ColumnInfo(name = "base_power") val basePower: Int
)
