package com.example.todoapplication.domain.repository

class EmployeeSpecialtyRepository() {

/*    private var employeeList =
        MutableLiveData<MutableList<Employee>>().apply { value = mutableListOf() }*/

   /* suspend fun getEmployee(specialtyId: Int): MutableLiveData<MutableList<Employee>> =
        withContext(Dispatchers.IO) {
            val specialtyList = employeeDao.getAllEmployeeBySpecialty(specialtyId)
            val employeeListWithSpecialty: MutableList<Employee> = mutableListOf()
            var userCounter = 0

            while (userCounter < specialtyList.size) {
                employeeDao.getEmployeeById(specialtyList[userCounter].userId).let {
                    employeeListWithSpecialty.add(it)
                }

                userCounter++
            }
            MutableLiveData(employeeListWithSpecialty).also { employeeList = it }
        }
*/
}