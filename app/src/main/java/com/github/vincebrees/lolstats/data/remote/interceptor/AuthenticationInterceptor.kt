package com.github.vincebrees.lolstats.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response


class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder()
    .header("X-Riot-Token", "RGAPI-2da70cbb-89f9-44bd-a443-28080ca9331a")
    .build())
    }

}