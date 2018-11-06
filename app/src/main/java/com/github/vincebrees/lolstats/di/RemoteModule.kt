package com.github.vincebrees.lolstats.di

import com.github.vincebrees.lolstats.data.remote.RestApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RemoteModule {

  @Provides @Singleton fun provideGson(): Gson =
      GsonBuilder()
          .setLenient()
          .create()

  @Provides @Singleton fun provideOkHttpClient(): OkHttpClient =
      OkHttpClient.Builder()
          .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
          .build()


  @Provides @Singleton fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
      Retrofit.Builder()
          .baseUrl("https://euw1.api.riotgames.com")
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(okHttpClient)
          .build()


  @Provides @Singleton fun provideRestApiService(retrofit: Retrofit): RestApiService =
      retrofit.create(RestApiService::class.java)

}


