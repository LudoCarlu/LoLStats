package com.github.vincebrees.lolstats.data.repository.summoner

import com.github.vincebrees.lolstats.data.entity.Summoner


interface SummonerRepository {
    fun getActualSummoner() : Summoner
}
