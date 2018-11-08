package com.github.vincebrees.lolstats.presentation.choosesummoner

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepository
import com.github.vincebrees.lolstats.di.LoLStatsApplication
import com.github.vincebrees.lolstats.domain.response.DataResponse
import com.github.vincebrees.lolstats.domain.response.ErrorResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChooseSummonerViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var summonerRepository: SummonerRepository

    private val compositeDisposable = CompositeDisposable()

    private val liveChooseSummonerState: MutableLiveData<ChooseSummonerViewState> = MutableLiveData()

    init {
        initializeDagger()

        liveChooseSummonerState.value = ChooseSummonerViewState(false, false, false)
    }

    private fun initializeDagger() = LoLStatsApplication.appComponent.inject(this)

    fun onClickedValidate(summonerName : String){
        liveChooseSummonerState.value = liveChooseSummonerState.value?.copy(isLoading = true)

        val disposable = summonerRepository.retrieveSummonerIdByName(summonerName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ typeReponse ->
                when(typeReponse){
                    is DataResponse -> liveChooseSummonerState.value = liveChooseSummonerState.value?.copy(
                        isLoading = false,
                        isError = false,
                        isSuccess = true)
                    is ErrorResponse -> liveChooseSummonerState.value = liveChooseSummonerState.value?.copy(
                        isLoading = false,
                        isError = true,
                        isSuccess = false)
                }
            }, { t: Throwable? ->
                liveChooseSummonerState.value = liveChooseSummonerState.value?.copy(isLoading = false, isError = false)
                t!!.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }
}


