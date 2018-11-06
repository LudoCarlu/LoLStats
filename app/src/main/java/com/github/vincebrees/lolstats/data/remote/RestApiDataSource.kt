package com.github.vincebrees.lolstats.data.remote

import javax.inject.Inject

class RestApiDataSource @Inject constructor(private val restApiService: RestApiService) {

    fun getSummonerIdByName(name: String) = restApiService.getSummonerIdByName(name)
}