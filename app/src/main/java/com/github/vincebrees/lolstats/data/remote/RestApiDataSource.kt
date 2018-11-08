package com.github.vincebrees.lolstats.data.remote

import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class RestApiDataSource @Inject constructor(private val restApiService: RestApiService) {

    fun getSummonerIdByName(name: String): Single<Response<RestSummoner>> {
        return restApiService.getSummonerIdByName(name)
    }
}