package com.jimmy.courseandroid.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jimmy.courseandroid.data.dao.CharacterDbzDao
import com.jimmy.courseandroid.data.entity.CharacterDbzEntity

@Database(entities = [CharacterDbzEntity::class], version = 2, exportSchema = false)
abstract class CharacterDbzDatabase: RoomDatabase() {

    companion object {
        private var database: CharacterDbzDatabase? = null
        private const val DATABASE_NAME = "character_dbz_database"

        fun getDatabase(context: Context): CharacterDbzDatabase {
            if (database == null) {
                database = Room.databaseBuilder(context, CharacterDbzDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return database!!
        }
    }

    abstract fun getDao(): CharacterDbzDao

}