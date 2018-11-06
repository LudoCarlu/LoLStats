package com.github.vincebrees.lolstats.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.vincebrees.lolstats.data.entity.Summoner

@Database(
    entities = arrayOf(Summoner::class),
    version = 1,
    exportSchema = false)
abstract class RoomDataSource : RoomDatabase() {

  abstract fun roomDao(): RoomDao

  companion object {
    fun buildDatabase(context: Context): RoomDataSource = Room.databaseBuilder(
        context.applicationContext,
        RoomDataSource::class.java,
      RoomContract.DATABASE_NAME
    ).build()
  }

}

