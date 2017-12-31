package com.fabianmagrini.footballfan.data.network.providers

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit by lazy { makeRetrofit() }

private fun makeRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://afternoon-gorge-60512.herokuapp.com/api/")
        .client(makeHttpClient())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

private fun makeHttpClient() = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(makeHeadersInterceptor())
        .addInterceptor(makeLoggingInterceptor())
        .build()
