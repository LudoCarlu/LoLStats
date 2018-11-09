package com.github.vincebrees.lolstats.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.github.vincebrees.lolstats.data.entity.Summoner
import io.reactivex.Single

@Dao
interface RoomDao {

  @Query(RoomContract.SELECT_SUMMONER)
  fun getActualSummoner(): Single<Summoner>

  @Insert
  fun insertActualSummoner(summoner: Summoner)

  @Query(RoomContract.DELETE_SUMMONER)
  fun deleteActualSummoner()
}

