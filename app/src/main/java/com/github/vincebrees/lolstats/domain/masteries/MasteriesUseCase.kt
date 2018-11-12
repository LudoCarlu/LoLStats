package com.github.vincebrees.lolstats.domain.masteries

import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.domain.models.ChampionMasteriesModel
import com.github.vincebrees.lolstats.domain.response.TypeResponse
import io.reactivex.Single

/**
 * Created by Vincent ETIENNE on 09/11/2018.
 */

interface MasteriesUseCase {
    fun getListChampion() : Single<TypeResponse<List<ChampionMasteriesModel>>>
}


