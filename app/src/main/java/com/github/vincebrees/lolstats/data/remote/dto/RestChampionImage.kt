package com.github.vincebrees.lolstats.data.remote.dto

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import java.util.Collections.emptyList
import com.google.gson.Gson



/**
 * Created by Vincent ETIENNE on 10/11/2018.
 */

data class RestChampionImage(
    val full : String,
    val sprite: String,
    val group: String,
    val x : Int,
    val y : Int,
    val w : Int,
    val h : Int
)

class RestChampionImageConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToObject(data: String?): RestChampionImage {
        val listType = object : TypeToken<RestChampionImage>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun objectToString(someObjects: RestChampionImage): String {
        return gson.toJson(someObjects)
    }
}