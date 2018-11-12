package com.github.vincebrees.lolstats.data.remote

import com.github.vincebrees.lolstats.data.remote.dto.RestStaticChampionData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RestStaticDataService {
  @GET("/cdn/6.24.1/data/fr_FR/champion.json")
  fun getListStaticChampionData(): Single<Response<RestStaticChampionData>>
}


