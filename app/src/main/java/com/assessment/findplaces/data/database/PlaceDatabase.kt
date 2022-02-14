package com.assessment.findplaces.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assessment.findplaces.data.database.dao.PlaceDao
import com.assessment.findplaces.data.database.entities.EntityPhoto
import com.assessment.findplaces.data.database.entities.EntityPlace
import com.assessment.findplaces.data.database.entities.EntityReview


@Database(entities = [EntityPlace::class, EntityReview::class, EntityPhoto::class], version = 1, exportSchema = false)
abstract class PlaceDatabase : RoomDatabase() {
    abstract val placeDao : PlaceDao
}

private lateinit var INSTANCE : PlaceDatabase

fun getDatabase(context: Context) : PlaceDatabase {
    synchronized(PlaceDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PlaceDatabase::class.java,
                "place"
            ).build()
        }
    }
    return INSTANCE
}