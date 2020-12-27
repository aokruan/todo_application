package com.example.todoapplication.domain.repository

import android.util.Log
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.domain.entity.UserNet
import com.example.todoapplication.domain.service.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userApi: UserApi, val userDao: UserDao) {

    val getAllUsers = userDao.getAll()
    val getAllSpecialty = userDao.getAllSpecialty()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val responseFromApi = userApi.getAllUsers().await()

//            //Обрабатываем битые поля
//            val data: MutableList<User> = mutableListOf()
//            var i = 0
//            while (i < responseFromApi.response.size) {
//                data.add(responseFromApi.response[i].format())
//                i++
//            }

            fun insertUserReturnId(user: UserNet): Long {
                return userDao.insertUser(
                    User(
                        0,
                        user.fName,
                        user.lName,
                        user.birthday,
                        user.birthday,
                        user.avatrUrl
                    ).format()
                )
            }

            fun insertSpecialityRelation(user: UserNet, userId: Long) {
                var specialityCounter = 0
                while (specialityCounter < user.specialty.size) {
                    userDao.insertSpecialty(
                        Specialty(
                            0,
                            user.specialty[specialityCounter].specialtyId,
                            user.specialty[specialityCounter].name,
                            userId
                        )
                    )
                    specialityCounter++
                }
            }

            var userListCounter = 0
            while (userListCounter < responseFromApi.response.size) {
                var user = responseFromApi.response[userListCounter]
                //insertUserReturnId(user)
                insertSpecialityRelation(user, insertUserReturnId(user))
                userListCounter++
            }

            val d0 = userDao.getAllSpecialtyNoLiveData()
            d0
            Log.e("Debug", "DB: SPECIALITY (UserRepository)" + d0.toString())

            val d1 = userDao.getAll1()
            d1
            Log.e("Debug", "DB: USER" + d1.toString())

            //userDao.insertAll(data)
        }
    }
}