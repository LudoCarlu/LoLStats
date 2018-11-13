package com.github.vincebrees.lolstats.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response


class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder()
    .header("X-Riot-Token", "RGAPI-caf31850-5009-4b54-b863-9210923265d5")
    .build())
    }

}