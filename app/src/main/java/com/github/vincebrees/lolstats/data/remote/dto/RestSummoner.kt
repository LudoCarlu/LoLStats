package com.github.vincebrees.lolstats.data.remote.dto

data class RestSummoner(
    val id: Int,
    val accountId: Int,
    val name: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val summonerLevel: Int
)