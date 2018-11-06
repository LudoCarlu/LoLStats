package com.github.vincebrees.lolstats.data.repository.summoner

import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.remote.RestSummoner
import javax.inject.Inject

class SummonerMapper @Inject constructor(){
    fun mapAsSummoner(restSummoner: RestSummoner): Summoner {
        return Summoner(restSummoner.name, restSummoner.summonerLevel)
    }
}
