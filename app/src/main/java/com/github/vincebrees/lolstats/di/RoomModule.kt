package com.github.vincebrees.lolstats.di
import android.content.Context
import com.github.vincebrees.lolstats.data.database.RoomDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

  @Provides @Singleton fun provideRoomCurrencyDataSource(context: Context) =
      RoomDataSource.buildDatabase(context)
}


