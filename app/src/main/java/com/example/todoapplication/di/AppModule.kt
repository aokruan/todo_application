package com.example.todoapplication.di

import android.app.Application
import com.example.todoapplication.domain.repository.ImagesRepository
import com.example.todoapplication.domain.service.api.ImagesApi
import com.example.todoapplication.presentation.viewModel.image_details.ImageDetailsViewModel
import com.example.todoapplication.presentation.viewModel.images.ImagesViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { ImagesViewModel(get()) }
    viewModel { ImageDetailsViewModel(get()) }
}

val apiModule = module {
    fun provideImagesApi(retrofit: Retrofit): ImagesApi =
        retrofit.create(ImagesApi::class.java)

    single { provideImagesApi(get()) }
}

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient = OkHttpClient.Builder().cache(cache).build()

    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/v2/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val repositoryModule = module {
    fun provideImagesRepository(imagesApi: ImagesApi): ImagesRepository =
        ImagesRepository(imagesApi)

    single { provideImagesRepository(get()) }
}