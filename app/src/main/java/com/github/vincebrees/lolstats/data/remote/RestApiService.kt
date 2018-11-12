package com.github.vincebrees.lolstats.data.remote

import com.github.vincebrees.lolstats.data.remote.dto.RestChampionMasteries
import com.github.vincebrees.lolstats.data.remote.dto.RestSummoner
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApiService {
  @GET("/lol/summoner/v3/summoners/by-name/{summoner_name}")
  fun getSummonerIdByName(@Path(value = "summoner_name", encoded = true) userId : String): Single<Response<RestSummoner>>

  @GET("/lol/champion-mastery/v3/champion-masteries/by-summoner/{summoner_id}")
  fun getListChampionMasteriesPoint(@Path(value = "summoner_id", encoded = true) summonerId: Int) : Single<Response<List<RestChampionMasteries>>>
}


