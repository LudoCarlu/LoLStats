package com.github.vincebrees.lolstats.data.repository.summoner

import com.github.vincebrees.lolstats.data.database.RoomDataSource
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.remote.RestApiDataSource
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

    override fun getActualSummoner() = roomDataSource.roomDao().getActualSummoner()

    override fun retrieveSummonerIdByName(name: String): Single<TypeResponse<Summoner>> {
        return restApiDataSource.getSummonerIdByName(name)
            .map {response ->
                if(response.isSuccessful && response.body() != null){

                    saveIntoDb(mapper.mapAsSummoner(response.body()!!))
                    roomDataSource.roomDao().getActualSummoner()
                    DataResponse(mapper.mapAsSummoner(response.body()!!))
                }else{
                    ErrorResponse<Summoner>(SummonerIdErrorCode.TECHNICAL_ERROR)
                }
            }
    }

    private fun saveIntoDb(summoner: Summoner) {
        roomDataSource.roomDao().deleteActualSummoner()
        roomDataSource.roomDao().insertActualSummoner(summoner)
    }
}
