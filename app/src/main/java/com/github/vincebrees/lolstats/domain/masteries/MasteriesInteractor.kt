package com.github.vincebrees.lolstats.domain.masteries

import com.github.vincebrees.lolstats.data.entity.Champion
import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.repository.staticdata.StaticDataRepository
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepository
import com.github.vincebrees.lolstats.domain.models.ChampionMasteriesModel
import com.github.vincebrees.lolstats.domain.response.DataResponse
import com.github.vincebrees.lolstats.domain.response.ErrorResponse
import com.github.vincebrees.lolstats.domain.response.TypeResponse
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by Vincent ETIENNE on 10/11/2018.
 */

class MasteriesInteractor @Inject constructor(
    private val summonerRepository: SummonerRepository,
    private val staticDataRepository: StaticDataRepository
): MasteriesUseCase {


    override fun getListChampion(): Single<TypeResponse<List<ChampionMasteriesModel>>> {
        return Single.zip(
            summonerRepository.getListChampionMasteries(),
            staticDataRepository.getListChampion(),
            BiFunction<TypeResponse<List<ChampionMasteries>>, TypeResponse<List<Champion>>,TypeResponse<List<ChampionMasteriesModel>>> { listChampionMasteries, listChampion ->
                if(listChampionMasteries is DataResponse<List<ChampionMasteries>>
                    && listChampion is DataResponse<List<Champion>>){
                    val listModel = mutableListOf<ChampionMasteriesModel>()
                    for (championMasteries in listChampionMasteries.data) {
                        val championName = findChampionNameWithId(championMasteries.championId, listChampion.data)
                        if(!championName.isNullOrEmpty())
                            listModel.add(mapAsChampionModel(championMasteries, championName))
                    }
                    return@BiFunction DataResponse(listModel)
                }else{
                    return@BiFunction ErrorResponse<List<ChampionMasteriesModel>>()
                }
            })
//        return Single.zip(
//            summonerRepository.getListChampionMasteries(),
//            staticDataRepository.getListChampion(),
//            BiFunction { listChampionMasteries, listChampion ->
//                if(listChampionMasteries is DataResponse<List<ChampionMasteries>>
//                && listChampion is DataResponse<List<Champion>>){
//                    val listModel = mutableListOf<ChampionMasteriesModel>()
//                    for (championMasteries in listChampionMasteries.data) {
//                        val championName = findChampionNameWithId(championMasteries.championId, listChampion.data)
//                        if(!championName.isNullOrEmpty())
//                            listModel.add(mapAsChampionModel(championMasteries, championName))
//                    }
//                    return@BiFunction DataResponse(listModel)
//                }else{
//                    return@BiFunction ErrorResponse<List<ChampionMasteriesModel>>()
//                }
//        })
    }

    private fun mapAsChampionModel(champion: ChampionMasteries, championName: String): ChampionMasteriesModel {
        return ChampionMasteriesModel(
            championName,
            champion.chestGranted,
            champion.championPoints
        )
    }

    private fun findChampionNameWithId(championId: Long, listChampion: List<Champion>) : String? {
        for (champion in listChampion) {
            if(champion.key.equals(championId.toString(), true)){
                return champion.name
            }
        }
        return null
    }

}