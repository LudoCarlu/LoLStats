package com.github.vincebrees.lolstats.data.repository.staticdata

import com.github.vincebrees.lolstats.data.entity.Champion
import com.github.vincebrees.lolstats.data.remote.dto.RestStaticChampionData
import javax.inject.Inject

/**
 * Created by Vincent ETIENNE on 10/11/2018.
 */

class StaticDataMapper @Inject constructor(){

    fun mapAsListChampion(restStaticChampionData: RestStaticChampionData): List<Champion> {
        var listEntity = arrayListOf<Champion>()

        for (item in restStaticChampionData.data) {
            listEntity.add(
                Champion(
                    item.key,
                    item.id,
                    item.name,
                    item.title,
                    item.description,
                    item.icon)
            )
        }
        return listEntity
    }
}