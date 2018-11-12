package com.github.vincebrees.lolstats.data.remote.dto

/**
 * Created by Vincent ETIENNE on 10/11/2018.
 */

data class RestChampion(
    val id : String,
    val key : String,
    val name : String,
    val title : String,
    val description : String,
    val icon : String
)