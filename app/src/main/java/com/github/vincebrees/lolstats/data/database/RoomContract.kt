package com.github.vincebrees.lolstats.data.database

class RoomContract {

  companion object {

    const val DATABASE_NAME = "lolstats.db"

    const val TABLE_SUMMONER = "summoner"
    const val TABLE_CHAMPION = "champion"
    const val TABLE_CHAMPION_MASTERIES = "champion_masteries"

    private const val SELECT_FROM = "SELECT * FROM "
    private const val DELETE_FROM = "DELETE FROM "

    const val SELECT_SUMMONER = SELECT_FROM + TABLE_SUMMONER
    const val DELETE_SUMMONER = DELETE_FROM + TABLE_SUMMONER

    const val SELECT_LIST_CHAMPION_MASTERIES = SELECT_FROM + TABLE_CHAMPION_MASTERIES
    const val DELETE_LIST_CHAMPION_MASTERIES = DELETE_FROM + TABLE_CHAMPION_MASTERIES

    const val SELECT_LIST_CHAMPION = SELECT_FROM + TABLE_CHAMPION
    const val DELETE_LIST_CHAMPION = DELETE_FROM + TABLE_CHAMPION

  }
}

