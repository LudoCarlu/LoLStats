package com.github.vincebrees.lolstats.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.github.vincebrees.lolstats.data.database.RoomContract
import com.github.vincebrees.lolstats.data.remote.dto.RestChampionImage
import com.github.vincebrees.lolstats.data.remote.dto.RestChampionImageConverter

/**
 * Created by Vincent ETIENNE on 09/11/2018.
 */


@Entity(tableName = RoomContract.TABLE_CHAMPION)
data class Champion(
    @PrimaryKey(autoGenerate = false)
    val key : String,
    val id : String,
    val name : String,
    val title : String,
    val description : String,
    val icon : String
)