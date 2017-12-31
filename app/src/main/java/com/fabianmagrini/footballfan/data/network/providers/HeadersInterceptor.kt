package com.fabianmagrini.footballfan.data.network.providers

import okhttp3.Interceptor

fun makeHeadersInterceptor() = Interceptor { chain -> // 1
    chain.proceed(chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build())
}