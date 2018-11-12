package com.github.vincebrees.lolstats.presentation.choosesummoner

import com.github.vincebrees.lolstats.domain.response.ErrorResponse
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode

/**
 * Created by Vincent ETIENNE on 08/11/2018.
 */

data class ChooseSummonerViewState(
    val isLoading: Boolean,
    val isError: SummonerIdErrorCode ?= null,
    val isSuccess: Boolean
)