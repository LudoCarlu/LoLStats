package com.github.vincebrees.lolstats.data.database

class RoomContract {

  companion object {

    const val DATABASE_NAME = "lolstats.db"

    const val TABLE_SUMMONER = "summoner"

    private const val SELECT_FROM = "SELECT * FROM "
    private const val DELETE_FROM = "DELETE FROM "

    const val SELECT_SUMMONER = SELECT_FROM + TABLE_SUMMONER
    const val DELETE_SUMMONER = DELETE_FROM + TABLE_SUMMONER

  }
}

