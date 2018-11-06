package com.github.vincebrees.lolstats.di

import com.github.vincebrees.lolstats.presentation.choosesummoner.ChooseSummonerViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RoomModule::class, RemoteModule::class))
@Singleton interface AppComponent {

  fun inject(viewModel: ChooseSummonerViewModel)

}


