package com.github.vincebrees.lolstats.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApiService {
  @GET("/lol/summoner/v3/summoners/by-name/{summoner_name}")
  fun getSummonerIdByName(@Path(value = "summoner_name", encoded = true) userId : String): Single<RestSummoner>

}


