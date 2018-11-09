package com.github.vincebrees.lolstats.presentation.masteries

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepository
import com.github.vincebrees.lolstats.di.LoLStatsApplication
import com.github.vincebrees.lolstats.domain.response.DataResponse
import com.github.vincebrees.lolstats.domain.response.ErrorResponse
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MasteriesViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var summonerRepository: SummonerRepository

    private val compositeDisposable = CompositeDisposable()

    private val liveMasteriesState: MutableLiveData<MasteriesViewState> = MutableLiveData()

    init {
        initializeDagger()

        liveMasteriesState.value = MasteriesViewState(false, false)
    }

    private fun initializeDagger() = LoLStatsApplication.appComponent.inject(this)

    fun getLiveDataState() = liveMasteriesState
}


