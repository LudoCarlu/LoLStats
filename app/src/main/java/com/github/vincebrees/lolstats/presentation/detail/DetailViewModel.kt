package com.github.vincebrees.lolstats.presentation.detail

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.github.vincebrees.lolstats.data.entity.Champion
import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.repository.staticdata.StaticDataRepository
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepository
import com.github.vincebrees.lolstats.di.LoLStatsApplication
import com.github.vincebrees.lolstats.domain.masteries.MasteriesUseCase
import com.github.vincebrees.lolstats.domain.models.ChampionMasteriesModel
import com.github.vincebrees.lolstats.domain.response.DataResponse
import com.github.vincebrees.lolstats.domain.response.ErrorResponse
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var staticDataRepository : StaticDataRepository

    private val compositeDisposable = CompositeDisposable()

    private val liveDataChampion: MutableLiveData<Champion> = MutableLiveData()

    init {
        initializeDagger()
    }

    fun onStart(championId : String){
        staticDataRepository.getListChampion()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                    listChampion -> if(listChampion is DataResponse<List<Champion>>){
                val champion = findIdWithList(championId, listChampion.data)
                liveDataChampion.value = champion
            }else{
                //TODO Handle error
            }
            }
    }

    private fun findIdWithList(championName: String, listChampion: List<Champion>) : Champion? {
        for (champion in listChampion) {
            if (champion.name.equals(championName)) {
                return champion
            }
        }
        return null
    }

    private fun initializeDagger() = LoLStatsApplication.appComponent.inject(this)

    fun getLiveDataChampion() = liveDataChampion
}


