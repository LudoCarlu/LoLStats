package com.github.vincebrees.lolstats.data.remote

import com.github.vincebrees.lolstats.data.remote.dto.RestChampionMasteries
import com.github.vincebrees.lolstats.data.remote.dto.RestStaticChampionData
import com.github.vincebrees.lolstats.data.remote.dto.RestSummoner
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class RestApiDataSource @Inject constructor(
    private val restApiService: RestApiService,
    private val restStaticDataService: RestStaticDataService) {

    fun getListStaticChampion() : Single<Response<RestStaticChampionData>> = restStaticDataService.getListStaticChampionData()

    fun getSummonerIdByName(name: String): Single<Response<RestSummoner>> = restApiService.getSummonerIdByName(name)

    fun getListChampionMasteries(summonerId : Int) : Single<Response<List<RestChampionMasteries>>> = restApiService.getListChampionMasteriesPoint(summonerId)
}