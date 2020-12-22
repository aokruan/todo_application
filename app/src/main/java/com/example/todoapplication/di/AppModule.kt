package com.example.todoapplication.di

import android.app.Application
import androidx.room.Room
import com.example.todoapplication.domain.AppDatabase
import com.example.todoapplication.domain.dao.TodoDao
import com.example.todoapplication.domain.repository.TodoRepository
import com.example.todoapplication.presentation.viewModel.todo.TodoDetailsViewModel
import com.example.todoapplication.presentation.viewModel.todo.TodoListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TodoListViewModel(get(), get()) }
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

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        return TodoRepository(dao)
    }

    single { provideTodoRepository(get()) }
}

