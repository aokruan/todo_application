package com.example.todoapplication.di

import android.app.Application
import androidx.room.Room
import com.example.todoapplication.domain.AppDatabase
import com.example.todoapplication.domain.dao.TodoDao
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.repository.TodoRepository
import com.example.todoapplication.domain.repository.UserRepository
import com.example.todoapplication.domain.service.api.UserApi
import com.example.todoapplication.domain.service.api.ApiHolder
import com.example.todoapplication.presentation.viewModel.todo.TodoDetailsViewModel
import com.example.todoapplication.presentation.viewModel.todo.TodoListViewModel
import com.example.todoapplication.presentation.viewModel.user.UserViewModel
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

    viewModel { TodoListViewModel(get(), get()) }
    viewModel { UserViewModel(get()) }
    viewModel { TodoDetailsViewModel(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "todo_app")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    fun provideDao(database: AppDatabase): TodoDao {
        return database.todoDao()
    }

/*    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }*/

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    //single { provideUserDao(get()) }
}

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    single { provideUserApi(get()) }
}

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
                .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }


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
//    fun provideTodoRepository(dao: TodoDao): TodoRepository {
//        return TodoRepository(dao)
//    }

    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepository(userApi)
    }

    single { provideUserRepository(get()) }


//    single { provideTodoRepository(get()) }

}

val serviceModule = module {
    single { ApiHolder() }
}

