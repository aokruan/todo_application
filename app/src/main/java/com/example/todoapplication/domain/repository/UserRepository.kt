package com.example.todoapplication.domain.repository

import android.util.Log
import com.example.todoapplication.domain.dao.UserDao
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.User
import com.example.todoapplication.domain.entity.EmploeeNetwork
import com.example.todoapplication.domain.service.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userApi: UserApi, val userDao: UserDao) {

    val getAllUsers = userDao.getAll()
    val getAllSpecialty = userDao.getAllSpecialty()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val responseFromApi = userApi.getAllUsers().await()

            /*
            * Получаем данные из сети и формируем
            * структуру для создания связи между таблицами
            * id специальности - id сотрудника
            */

            fun insertUserReturnId(user: EmploeeNetwork): Long {
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

            /*
            * Формируем структуру для записи в таблицу специальностей,
            * структуру для создания связи между таблицами
            * id специальности - id сотрудника
            */

            fun insertSpecialityRelation(user: EmploeeNetwork, userId: Long) {
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
                insertSpecialityRelation(user, insertUserReturnId(user))
                userListCounter++
            }
        }
    }
}