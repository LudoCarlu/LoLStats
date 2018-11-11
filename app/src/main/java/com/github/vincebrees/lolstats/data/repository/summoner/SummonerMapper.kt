package com.github.vincebrees.lolstats.data.repository.summoner

import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.remote.dto.RestChampionMasteries
import com.github.vincebrees.lolstats.data.remote.dto.RestSummoner
import javax.inject.Inject

class SummonerMapper @Inject constructor(){
    fun mapAsSummoner(restSummoner: RestSummoner): Summoner {
        return Summoner(restSummoner.id, restSummoner.name, restSummoner.summonerLevel)
    }

    fun mapAsListChampionMasteries(listRest: List<RestChampionMasteries>): List<ChampionMasteries> {
        var listEntity = arrayListOf<ChampionMasteries>()
        for (item in listRest) {
            listEntity.add(ChampionMasteries(
                item.championId,
                item.championLevel,
                item.chestGranted,
                item.championPoints,
                item.lastPlayTime))
        }
        return listEntity
    }
}
