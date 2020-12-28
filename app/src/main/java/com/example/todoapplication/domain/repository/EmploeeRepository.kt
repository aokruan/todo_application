package com.example.todoapplication.domain.repository

import com.example.todoapplication.domain.dao.EmploeeDao
import com.example.todoapplication.domain.dao.SpecaltyDao
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.Emploee
import com.example.todoapplication.domain.entity.EmploeeNetwork
import com.example.todoapplication.domain.service.api.EmploeeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmploeeRepository(private val emploeeApi: EmploeeApi, val emploeeDao: EmploeeDao, val specaltyDao: SpecaltyDao) {
    val getAllSpecialty = specaltyDao.getAllSpecialty()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val responseFromApi = emploeeApi.getAllEmploee().await()

            /*
            * Получаем данные из сети и формируем
            * структуру для создания связи между таблицами
            * id специальности - id сотрудника
            */

            fun insertEmploeeReturnId(emploee: EmploeeNetwork): Long {
                return emploeeDao.insertEmploee(
                    Emploee(
                        0,
                        emploee.fName,
                        emploee.lName,
                        emploee.birthday,
                        emploee.birthday,
                        emploee.avatrUrl
                    ).format()
                )
            }

            /*
            * Формируем структуру для записи в таблицу специальностей,
            * структуру для создания связи между таблицами
            * id специальности - id сотрудника
            */

            fun insertSpecialityRelation(emploeeNetwork: EmploeeNetwork, emploeeId: Long) {
                var specialityCounter = 0
                while (specialityCounter < emploeeNetwork.specialty.size) {
                    specaltyDao.insertSpecialty(
                        Specialty(
                            0,
                            emploeeNetwork.specialty[specialityCounter].specialtyId,
                            emploeeNetwork.specialty[specialityCounter].name,
                            emploeeId
                        )
                    )
                    specialityCounter++
                }
            }

            var emploeeListCounter = 0
            while (emploeeListCounter < responseFromApi.response.size) {
                var emploee = responseFromApi.response[emploeeListCounter]
                insertSpecialityRelation(emploee, insertEmploeeReturnId(emploee))
                emploeeListCounter++
            }
        }
    }
}