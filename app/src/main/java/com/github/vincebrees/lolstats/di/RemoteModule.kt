package com.github.vincebrees.lolstats.di

import com.github.vincebrees.lolstats.data.remote.RestApiService
import com.github.vincebrees.lolstats.data.remote.RestStaticDataService
import com.github.vincebrees.lolstats.data.remote.interceptor.AuthenticationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class RemoteModule {

  @Provides @Singleton fun provideGson(): Gson =
      GsonBuilder()
          .setLenient()
          .create()

    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(): AuthenticationInterceptor = AuthenticationInterceptor()

  @Provides @Singleton fun provideOkHttpClient(authenticationInterceptor: AuthenticationInterceptor): OkHttpClient =
      OkHttpClient.Builder()
          .addInterceptor(authenticationInterceptor)
          .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
          .connectTimeout(30, TimeUnit.SECONDS)
          .build()

  @Provides @Singleton fun provideRestApiService(gson: Gson, okHttpClient: OkHttpClient): RestApiService =
      Retrofit.Builder()
          .baseUrl("https://euw1.api.riotgames.com")
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(okHttpClient)
          .build()
          .create(RestApiService::class.java)

    @Provides @Singleton fun provideRestStaticDataService(gson: Gson, okHttpClient: OkHttpClient): RestStaticDataService =
        Retrofit.Builder()
            .baseUrl("https://ddragon.leagueoflegends.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RestStaticDataService::class.java)
}


