package com.github.vincebrees.lolstats.domain.models

/**
 * Created by Vincent ETIENNE on 10/11/2018.
 */

data class ChampionMasteriesModel constructor(
    val name : String,
    val chestGranted : Boolean,
    val championPoints : Int
)