package com.github.vincebrees.lolstats.data.repository.summoner

import com.github.vincebrees.lolstats.data.database.RoomDataSource
import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.remote.RestApiDataSource
import com.github.vincebrees.lolstats.data.remote.dto.RestChampionMasteries
import com.github.vincebrees.lolstats.domain.response.DataResponse
import com.github.vincebrees.lolstats.domain.response.ErrorResponse
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode
import com.github.vincebrees.lolstats.domain.response.TypeResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SummonerRepositoryImpl @Inject constructor(
    private val roomDataSource: RoomDataSource,
    private val restApiDataSource: RestApiDataSource,
    private val mapper: SummonerMapper
) : SummonerRepository {

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    private lateinit var actualSummoner : Summoner

    private fun getActualSummoner() = roomDataSource.roomDao().getActualSummoner()

    override fun retrieveSummonerIdByName(name: String): Single<TypeResponse<Summoner>> {
        return restApiDataSource.getSummonerIdByName(name)
            .map {response ->
                if(response.isSuccessful && response.body() != null){
                    saveSummonerIntoDb(mapper.mapAsSummoner(response.body()!!))
                    roomDataSource.roomDao().getActualSummoner()
                    DataResponse(mapper.mapAsSummoner(response.body()!!))
                }else{
                    ErrorResponse<Summoner>(SummonerIdErrorCode.TECHNICAL_ERROR)
                }
            }
    }

    private fun saveSummonerIntoDb(summoner: Summoner) {
        actualSummoner = summoner
        roomDataSource.roomDao().deleteActualSummoner()

        roomDataSource.roomDao().insertActualSummoner(summoner)
    }


    override fun getListChampionMasteries(): Single<TypeResponse<List<ChampionMasteries>>> {
        return getActualSummoner()
            .flatMap { summoner ->
                restApiDataSource.getListChampionMasteries(summoner.summonerId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { response ->
                        if(response.isSuccessful && response.body() != null){
                            val list = mapper.mapAsListChampionMasteries(response.body()!!)
                            saveListChampionMasteries(list)
                            DataResponse(list)
                        }else {
                            ErrorResponse<List<ChampionMasteries>>()
                        }
                    }
            }
//                return restApiDataSource.getListChampionMasteries(actualSummoner.summonerId)
//                    .map { response ->
//                        if(response.isSuccessful && response.body() != null){
//                            val list = mapper.mapAsListChampionMasteries(response.body()!!)
//                            saveListChampionMasteries(list)
//                            DataResponse(list)
//                        }else {
//                            ErrorResponse<List<ChampionMasteries>>()
//                        }
//                    }
            }

    private fun saveListChampionMasteries(body: List<ChampionMasteries>) {

    }
}
