package com.github.vincebrees.lolstats.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.vincebrees.lolstats.data.database.RoomContract

@Entity(tableName = RoomContract.TABLE_SUMMONER)
data class Summoner(
    val name: String,
    val summonerLevel: Int
){
    @PrimaryKey(autoGenerate = false)
    var id: Long = 1
}