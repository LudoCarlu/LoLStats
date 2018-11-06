package com.github.vincebrees.lolstats.data.repository.summoner

import com.github.vincebrees.lolstats.data.database.RoomDataSource
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.remote.RestApiDataSource
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SummonerRepositoryImpl @Inject constructor(
    private val roomDataSource: RoomDataSource,
    private val restApiDataSource: RestApiDataSource,
    private val mapper: SummonerMapper
) : SummonerRepository {

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun getActualSummoner(): Summoner {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
