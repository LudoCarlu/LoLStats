package com.github.vincebrees.lolstats.data.repository.summoner

import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.remote.dto.RestChampionMasteries
import com.github.vincebrees.lolstats.domain.response.TypeResponse
import io.reactivex.Single


interface SummonerRepository {
    fun retrieveSummonerIdByName(name : String) : Single<TypeResponse<Summoner>>

    fun getListChampionMasteries() : Single<TypeResponse<List<ChampionMasteries>>>
}
