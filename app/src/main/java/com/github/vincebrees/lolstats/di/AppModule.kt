package com.github.vincebrees.lolstats.di

import android.content.Context
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepository
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: LoLStatsApplication) {

  @Provides @Singleton fun provideContext(): Context = application

  @Provides @Singleton fun provideSummonerRepository(instance: SummonerRepositoryImpl): SummonerRepository = instance

}


