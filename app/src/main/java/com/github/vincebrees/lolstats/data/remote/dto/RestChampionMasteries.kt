package com.github.vincebrees.lolstats.data.remote.dto

/**
 * Created by Vincent ETIENNE on 09/11/2018.
 */


data class RestChampionMasteries(
    val championLevel: Int,
    val chestGranted: Boolean,
    val championPoints: Int,
    val championPointsSinceLastLevel: Long,
    val playerId: Long,
    val championPointsUntilNextLevel: Long,
    val tokensEarned: Int,
    val championId: Long,
    val lastPlayTime: Long
)