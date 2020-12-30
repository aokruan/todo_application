package com.example.todoapplication.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat

@Parcelize
@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var firstName: String = "",
    var lastName: String = "",
    var birthday: String? = "",
    var age: String? = "",
    var avatrUrl: String? = null
) : Parcelable {

    // Преобразование к нижнему регистру. Устанавливает первый символ в верхний регистр
    fun fixFailureFields(string: String): String = (string.toLowerCase()).capitalize()

    // Преобразование даты рождения к единому формату
    // TODO: Не лучшее решение
    fun changeBirtday(birthday: String?): String {
        return when (birthday) {
            null -> "-"
            "" -> "-"
            else -> {
                // Результат преобразования даты
                var date = ""

                // В связи с разным форматом, пока такое решение
                try {
                    // Парсим строку под шаблон
                    val parsedInputDate =
                        DateTime.parse(birthday, DateTimeFormat.forPattern("dd-MM-yyyy"))

                    // Приводим к определённому DateTime формату
                    val dtf: DateTimeFormatter =
                        DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

                    // Парсим Date
                    val dateTime: DateTime =
                        dtf.parseDateTime(parsedInputDate.toString()) // 2000-07-23T00:00:00.000Z

                    // Приводим к стандарту
                    val dateTimeFormat = ISODateTimeFormat.dateTime()

                    // Приводим к необходимому шаблону
                    val dateWithCustomFormat =
                        dateTimeFormat.parseLocalDateTime(dateTime.toString())
                            .toString(
                                DateTimeFormat
                                    .forPattern("dd.MM.yyyy")
                            )

                    date = dateWithCustomFormat

                } catch (e: Exception) {
                    try {
                        // Парсим строку под шаблон
                        val parsedInputDate =
                            DateTime.parse(birthday, DateTimeFormat.forPattern("yyyy-MM-dd"))

                        // Приводим к определённому DateTime формату
                        val dtf: DateTimeFormatter =
                            DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

                        // Парсим Date
                        val dateTime: DateTime =
                            dtf.parseDateTime(parsedInputDate.toString()) // 2000-07-23T00:00:00.000Z

                        // Приводим к стандарту
                        val dateTimeFormat = ISODateTimeFormat.dateTime()

                        // Приводим к необходимому шаблону
                        val dateWithCustomFormat =
                            dateTimeFormat.parseLocalDateTime(dateTime.toString())
                                .toString(
                                    DateTimeFormat
                                        .forPattern("dd.MM.yyyy")
                                )

                        date = dateWithCustomFormat
                    } finally {
                    }
                }
                return date
            }
        }
    }

    // Преобразование даты рождения к единому формату
    fun calculateAge(birthday: String?): String {
        return when (birthday) {
            null -> "-"
            "" -> "-"
            else -> {
                // Результат преобразования даты
                var date = ""

                // В связи с разным форматом, пока такое решение
                try {
                    // Парсим строку под шаблон
                    val parsedInputDate =
                        DateTime.parse(birthday, DateTimeFormat.forPattern("dd-MM-yyyy"))

                    // Приводим к определённому DateTime формату
                    val dtf: DateTimeFormatter =
                        DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

                    // Парсим Date
                    val dateTime: DateTime =
                        dtf.parseDateTime(parsedInputDate.toString()) //2000-07-23T00:00:00.000Z

                    val start = DateTime(
                        dateTime.year,
                        dateTime.monthOfYear,
                        dateTime.dayOfWeek,
                        0,
                        0,
                        0,
                        0
                    )

                    // Вычисляем возраст
                    val period = Period(start, DateTime.now())

                    date = period.years.toString()

                } catch (e: Exception) {
                    try {
                        // Парсим строку под шаблон
                        val parsedInputDate =
                            DateTime.parse(birthday, DateTimeFormat.forPattern("yyyy-MM-dd"))

                        // Приводим к определённому DateTime формату
                        val dtf: DateTimeFormatter =
                            DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

                        // Парсим Date
                        val dateTime: DateTime =
                            dtf.parseDateTime(parsedInputDate.toString()) //2000-07-23T00:00:00.000Z

                        val start = DateTime(
                            dateTime.year,
                            dateTime.monthOfYear,
                            dateTime.dayOfWeek,
                            0,
                            0,
                            0,
                            0
                        )

                        val period = Period(start, DateTime.now())

                        date = period.years.toString()
                    } finally {
                    }
                }
                return date
            }
        }
    }

    fun format(): Employee {
        return Employee(
            id,
            fixFailureFields(firstName),
            fixFailureFields(lastName),
            changeBirtday(birthday),
            calculateAge(age),
            avatrUrl
        )
    }
}

@Parcelize
@Entity
data class Specialty(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var specialtyId: Int = 0,
    var name: String = "",
    var userId: Long = 0
) : Parcelable

