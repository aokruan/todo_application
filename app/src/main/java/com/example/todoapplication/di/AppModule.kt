package com.example.todoapplication.di

import android.app.Application
import androidx.room.Room
import com.example.todoapplication.domain.AppDatabase
import com.example.todoapplication.domain.dao.EmployeeDao
import com.example.todoapplication.domain.dao.SpecaltyDao
import com.example.todoapplication.domain.repository.EmployeeRepository
import com.example.todoapplication.domain.repository.EmployeeSpecialtyRepository
import com.example.todoapplication.domain.service.api.EmployeeApi
import com.example.todoapplication.presentation.viewModel.employee_specialty.EmployeeSpecialtyViewModel
import com.example.todoapplication.presentation.viewModel.speciality.SpecialityViewModel
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
    viewModel { EmployeeSpecialtyViewModel(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "employee_app")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideEmployeeDao(database: AppDatabase): EmployeeDao = database.employeeDao()
    fun provideSpecialtyDao(database: AppDatabase): SpecaltyDao = database.specialtyDao()

    single { provideDatabase(androidApplication()) }
    single { provideEmployeeDao(get()) }
    single { provideSpecialtyDao(get()) }
}

val apiModule = module {
    fun provideEmployeeApi(retrofit: Retrofit): EmployeeApi =
        retrofit.create(EmployeeApi::class.java)

    single { provideEmployeeApi(get()) }
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
    fun provideEmployeeRepository(
        employeeApi: EmployeeApi,
        employeeDao: EmployeeDao,
        specaltyDao: SpecaltyDao
    ): EmployeeRepository =
        EmployeeRepository(employeeApi, employeeDao, specaltyDao)

    fun provideEmployeeSpecialtyRepository(employeeDao: EmployeeDao): EmployeeSpecialtyRepository =
        EmployeeSpecialtyRepository(employeeDao)

    single { provideEmployeeSpecialtyRepository(get()) }
    single { provideEmployeeRepository(get(), get(), get()) }
}