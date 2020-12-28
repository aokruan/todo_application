package com.example.todoapplication.di

import android.app.Application
import androidx.room.Room
import com.example.todoapplication.domain.AppDatabase
import com.example.todoapplication.domain.dao.EmploeeDao
import com.example.todoapplication.domain.dao.SpecaltyDao
import com.example.todoapplication.domain.repository.EmploeeRepository
import com.example.todoapplication.domain.repository.EmploeeSpecialtyRepository
import com.example.todoapplication.domain.service.api.EmploeeApi
import com.example.todoapplication.presentation.viewModel.speciality.SpecialityViewModel
import com.example.todoapplication.presentation.viewModel.emploee_specialty.EmploeeSpecialtyViewModel
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
    viewModel { SpecialityViewModel(get()) }
    viewModel { EmploeeSpecialtyViewModel(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "emploee_app")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideEmploeeDao(database: AppDatabase): EmploeeDao = database.emploeeDao()
    fun provideSpecialtyDao(database: AppDatabase): SpecaltyDao = database.specialtyDao()

    single { provideDatabase(androidApplication()) }
    single { provideEmploeeDao(get()) }
    single { provideSpecialtyDao(get()) }
}

val apiModule = module {
    fun provideEmploeeApi(retrofit: Retrofit): EmploeeApi = retrofit.create(EmploeeApi::class.java)

    single { provideEmploeeApi(get()) }
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
            .baseUrl("http://gitlab.65apps.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val repositoryModule = module {
    fun provideEmploeeRepository(
        emploeeApi: EmploeeApi,
        emploeeDao: EmploeeDao,
        specaltyDao: SpecaltyDao
    ): EmploeeRepository =
        EmploeeRepository(emploeeApi, emploeeDao, specaltyDao)

    fun provideEmploeeSpecialtyRepository(emploeeDao: EmploeeDao): EmploeeSpecialtyRepository =
        EmploeeSpecialtyRepository(emploeeDao)

    single { provideEmploeeSpecialtyRepository(get()) }
    single { provideEmploeeRepository(get(), get(), get()) }
}