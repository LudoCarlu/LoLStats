package com.github.vincebrees.lolstats.data.database

class RoomContract {

  companion object {

    const val DATABASE_NAME = "lolstats.db"

    const val TABLE_SUMMONER = "summoner"

    private const val SELECT_FROM = "SELECT * FROM "

    const val SELECT_SUMMONER = SELECT_FROM + TABLE_SUMMONER

  }
}

