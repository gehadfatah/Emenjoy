package com.goda.emenjoy.model.common

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://emenjoy.com/Enjoy/public/"



val retrofitClient: Retrofit by lazy {
    Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
}

val okHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
}

val httpLoggingInterceptor by lazy {
    return@lazy HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
