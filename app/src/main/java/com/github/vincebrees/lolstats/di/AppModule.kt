package com.github.vincebrees.lolstats.di

import android.content.Context
import com.github.vincebrees.lolstats.data.repository.staticdata.StaticDataRepository
import com.github.vincebrees.lolstats.data.repository.staticdata.StaticDataRepositoryImpl
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepository
import com.github.vincebrees.lolstats.data.repository.summoner.SummonerRepositoryImpl
import com.github.vincebrees.lolstats.domain.masteries.MasteriesInteractor
import com.github.vincebrees.lolstats.domain.masteries.MasteriesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: LoLStatsApplication) {

  @Provides @Singleton fun provideContext(): Context = application

  @Provides @Singleton fun provideSummonerRepository(instance: SummonerRepositoryImpl): SummonerRepository = instance

  @Provides @Singleton fun provideStaticDataRepository(instance: StaticDataRepositoryImpl): StaticDataRepository = instance

  @Provides @Singleton fun provideMasteriesUseCase(instance: MasteriesInteractor): MasteriesUseCase = instance

}


