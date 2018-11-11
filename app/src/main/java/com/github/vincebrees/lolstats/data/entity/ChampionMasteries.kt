package com.github.vincebrees.lolstats.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.vincebrees.lolstats.data.database.RoomContract

/**
 * Created by Vincent ETIENNE on 09/11/2018.
 */


@Entity(tableName = RoomContract.TABLE_CHAMPION_MASTERIES)
data class ChampionMasteries(
    @PrimaryKey(autoGenerate = false)
    val championId: Long,
    val championLevel: Int,
    val chestGranted: Boolean,
    val championPoints: Int,
    val lastPlayTime: Long
)