package com.example.todoapplication.presentation.viewModel.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.dao.TodoDao
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.repository.TodoRepository
import com.example.todoapplication.domain.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TodoListViewModel(
        private val todoRepository: TodoRepository,
        private val todoDao: TodoDao
) : ViewModel() {

    //    val listOfUsers: BehaviorSubject<List<Response>> = BehaviorSubject.create()
//    private val disposable = CompositeDisposable()
    //val getAllUsers = userInteractor.getAllUsers()

    val getAllTodo = todoRepository.getAllTodo

    fun deleteTodoAsync(todo: Todo) = GlobalScope.async {
        todoDao.delete(todo)
    }

    fun insertTodoAsync(todo: Todo) = GlobalScope.async {
        todoRepository.insert(todo)
    }

//    fun getAllUsers() = GlobalScope.async {
//        userInteractor.getAllUsers()
//    }

//    override fun onCleared() {
//        disposable.clear()
//    }

    fun aaa(){
        Log.e("DDD", "VM ")
    }
//    fun getData() {
//
//
//        Log.e("DDD", "VM ")
//
//        val compositeDisposable = CompositeDisposable()
//        val interactor = userInteractor
//        compositeDisposable.add(
//                interactor.getAllUsers()
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnSubscribe {  }
//                        .doFinally {  }
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({ result ->
//                            Log.e("DDD", "VM " + result.toString())
//                        }, { error ->
//                            Log.e("DDD", "VM " + error.toString())
//                        })
//        )
//
//    }


}