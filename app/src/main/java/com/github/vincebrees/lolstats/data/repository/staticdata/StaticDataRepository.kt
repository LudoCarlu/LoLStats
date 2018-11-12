package com.github.vincebrees.lolstats.data.repository.staticdata

import com.github.vincebrees.lolstats.data.entity.Champion
import com.github.vincebrees.lolstats.domain.response.TypeResponse
import io.reactivex.Single

/**
 * Created by Vincent ETIENNE on 10/11/2018.
 */

interface  StaticDataRepository{
    fun getListChampion(): Single<TypeResponse<List<Champion>>>
}