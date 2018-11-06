package com.github.vincebrees.lolstats.presentation.choosesummoner

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepository
import com.github.vincebrees.lolstats.di.LoLStatsApplication
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChooseSummonerViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var summonerRepository: SummonerRepository

    private val compositeDisposable = CompositeDisposable()

    init {
        initializeDagger()
    }

    private fun initializeDagger() = LoLStatsApplication.appComponent.inject(this)

}


