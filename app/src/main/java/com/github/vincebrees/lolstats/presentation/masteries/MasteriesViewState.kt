package com.github.vincebrees.lolstats.presentation.masteries

import com.github.vincebrees.lolstats.domain.response.ErrorResponse
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode

/**
 * Created by Vincent ETIENNE on 08/11/2018.
 */

data class MasteriesViewState(
    val isLoading: Boolean,
    val isSuccess: Boolean
)