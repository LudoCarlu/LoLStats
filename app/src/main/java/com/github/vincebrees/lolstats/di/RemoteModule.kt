package com.github.vincebrees.lolstats.di

import com.github.vincebrees.lolstats.data.remote.RestApiService
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


