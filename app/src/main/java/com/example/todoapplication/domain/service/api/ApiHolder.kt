package com.example.todoapplication.domain.service.api

import com.example.todoapplication.App
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiHolder {
//    private val url = "http://gitlab.65apps.com/"
//
//    private val gson = App.instance.gson
//
//    val api: Api = initApi()
//
//    private fun initApi(): Api {
//        val httpClientBuilder = OkHttpClient.Builder()
//        val logging = HttpLoggingInterceptor().setLevel(Level.BODY)
//        httpClientBuilder.addInterceptor(logging)
//
//        return Retrofit.Builder()
//                .client(httpClientBuilder.build())
//                .baseUrl(url)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//                .build()
//                .create(Api::class.java)
//    }
}
