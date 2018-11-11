package com.github.vincebrees.lolstats.presentation.masteries

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.entity.Summoner
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

class MasteriesViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var masteriesUseCase : MasteriesUseCase

    private val compositeDisposable = CompositeDisposable()

    private val liveMasteriesState: MutableLiveData<MasteriesViewState> = MutableLiveData()
    private val liveListChampion: MutableLiveData<List<ChampionMasteriesModel>> = MutableLiveData()

    init {
        initializeDagger()

        liveMasteriesState.value = MasteriesViewState(true, false, false)
        val disposable = masteriesUseCase.getListChampion()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                when(list){
                    is DataResponse -> liveListChampion.value = list.data
                    is ErrorResponse -> liveMasteriesState.value = liveMasteriesState.value?.copy(
                        isLoading = false,
                        isError = true,
                        isEmptyList = false)
                }
            }, { t: Throwable? -> liveMasteriesState.value = liveMasteriesState.value?.copy(
                    isLoading = false,
                    isError = true,
                    isEmptyList = false)
                Log.d("BUG", t.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun initializeDagger() = LoLStatsApplication.appComponent.inject(this)

    fun getLiveDataState() = liveMasteriesState
    fun getLiveDataListChampionModel() = liveListChampion

}


