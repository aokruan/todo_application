package com.example.todoapplication.domain.repository

import com.example.todoapplication.domain.dao.EmployeeDao
import com.example.todoapplication.domain.dao.SpecaltyDao
import com.example.todoapplication.domain.entity.Specialty
import com.example.todoapplication.domain.entity.Employee
import com.example.todoapplication.domain.entity.EmployeeNetwork
import com.example.todoapplication.domain.service.api.EmployeeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepository(private val employeeApi: EmployeeApi, val employeeDao: EmployeeDao, val specaltyDao: SpecaltyDao) {
    val getAllSpecialty = specaltyDao.getAllSpecialtyGroupByName()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            employeeDao.dropEmployeeTable(employeeDao.getAllEmployee())
            employeeDao.dropSpecialtyTable(specaltyDao.getAllSpecialty())
            val responseFromApi = employeeApi.getAllEmployee().await()

            /*
            * Получаем данные из сети и формируем
            * структуру для создания связи между таблицами
            * id специальности - id сотрудника
            */

            fun insertEmployeeReturnId(employee: EmployeeNetwork): Long {
                return employeeDao.insertEmployee(
                    Employee(
                        0,
                        employee.fName,
                        employee.lName,
                        employee.birthday,
                        employee.birthday,
                        employee.avatrUrl
                    ).format()
                )
            }

            /*
            * Формируем структуру для записи в таблицу специальностей,
            * структуру для создания связи между таблицами
            * id специальности - id сотрудника
            */

            fun insertSpecialityRelation(employeeNetwork: EmployeeNetwork, employeeId: Long) {
                var specialityCounter = 0
                while (specialityCounter < employeeNetwork.specialty.size) {
                    specaltyDao.insertSpecialty(
                        Specialty(
                            0,
                            employeeNetwork.specialty[specialityCounter].specialtyId,
                            employeeNetwork.specialty[specialityCounter].name,
                            employeeId
                        )
                    )
                    specialityCounter++
                }
            }

            var employeeListCounter = 0
            while (employeeListCounter < responseFromApi.response.size) {
                var employee = responseFromApi.response[employeeListCounter]
                insertSpecialityRelation(employee, insertEmployeeReturnId(employee))
                employeeListCounter++
            }
        }
    }
}