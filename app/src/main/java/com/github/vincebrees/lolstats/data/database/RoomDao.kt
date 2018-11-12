package com.github.vincebrees.lolstats.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.github.vincebrees.lolstats.data.entity.Champion
import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
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

  @Insert
  fun insertListChampionMasteries(list: List<ChampionMasteries>)

  @Query(RoomContract.SELECT_LIST_CHAMPION_MASTERIES)
  fun getListChampionMasteries(): List<ChampionMasteries>

  @Insert
  fun insertListChampion(list: List<Champion>)

  @Query(RoomContract.DELETE_LIST_CHAMPION)
  fun deleteListChampion()

  @Query(RoomContract.SELECT_LIST_CHAMPION)
  fun getListChampion(): Single<List<Champion>>
}

