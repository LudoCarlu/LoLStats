package com.github.vincebrees.lolstats.di

import android.app.Application

class LoLStatsApplication : Application() {

  companion object {
    lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    initializeDagger()
  }

  fun initializeDagger() {
    appComponent = DaggerAppComponent.builder()
      .appModule(AppModule(this))
      .roomModule(RoomModule())
      .remoteModule(RemoteModule()).build()
  }
}

