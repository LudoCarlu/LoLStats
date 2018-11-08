package com.github.vincebrees.lolstats.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response


class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder()
    .header("X-Riot-Token", "RGAPI-2196ecbb-4d9e-4f64-8fd5-1c6c4a1a6ae2")
    .build())
    }

}