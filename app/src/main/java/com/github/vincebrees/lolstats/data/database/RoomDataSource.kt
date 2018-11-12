package com.github.vincebrees.lolstats.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.github.vincebrees.lolstats.data.entity.Champion
import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.remote.dto.RestChampionImageConverter

@Database(
    entities = arrayOf(Summoner::class, Champion::class, ChampionMasteries::class),
    version = 1,
    exportSchema = false)
@TypeConverters(RestChampionImageConverter::class)
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

