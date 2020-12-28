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

//data class UsersBase(@SerializedName("response") var response: MutableList<User> = mutableListOf())

@Parcelize
@Entity
data class User(
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
    // TODO: 27.12.20 Провести рефакторинг
    fun changeBirtday(birthday: String?): String {
        return when (birthday) {
            null -> "-"
            "" -> "-"
            else -> {
                var date = ""

                // В связи с разным форматом, пока такое решение
                try {
                    var pattern = DateTime.parse(birthday, DateTimeFormat.forPattern("dd-MM-yyyy"))
                    val pattern1 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

                    val dtf: DateTimeFormatter = DateTimeFormat.forPattern(pattern1)
                    val dateTime: DateTime =
                        dtf.parseDateTime(pattern.toString()) //2000-07-23T00:00:00.000Z

                    val pattern2 = "dd.MM.yyyy"

                    val dtf1 = ISODateTimeFormat.dateTime()
                    val parsedDate = dtf1.parseLocalDateTime(dateTime.toString())
                    val dateWithCustomFormat =
                        parsedDate.toString(DateTimeFormat.forPattern(pattern2))

                    date = dateWithCustomFormat

                } catch (e: Exception) {
                    try {
                        var pattern =
                            DateTime.parse(birthday, DateTimeFormat.forPattern("yyyy-MM-dd"))
                        val pattern1 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

                        val dtf: DateTimeFormatter = DateTimeFormat.forPattern(pattern1)
                        val dateTime: DateTime =
                            dtf.parseDateTime(pattern.toString()) //2000-07-23T00:00:00.000Z

                        val pattern2 = "dd.MM.yyyy"

                        val dtf1 = ISODateTimeFormat.dateTime()
                        val parsedDate = dtf1.parseLocalDateTime(dateTime.toString())
                        val dateWithCustomFormat =
                            parsedDate.toString(DateTimeFormat.forPattern(pattern2))
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
                var date = ""

                // В связи с разным форматом, пока такое решение
                try {
                    var pattern =
                        DateTime.parse(birthday, DateTimeFormat.forPattern("dd-MM-yyyy"))
                    val pattern1 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

                    val dtf: DateTimeFormatter = DateTimeFormat.forPattern(pattern1)
                    val dateTime: DateTime =
                        dtf.parseDateTime(pattern.toString()) //2000-07-23T00:00:00.000Z

                    val start = DateTime(
                        dateTime.year,
                        dateTime.monthOfYear,
                        dateTime.dayOfWeek,
                        0,
                        0,
                        0,
                        0
                    )
                    val endDate = DateTime.now()

                    val period = Period(start, endDate)

                    date = period.years.toString()

                } catch (e: Exception) {
                    try {
                        var pattern =
                            DateTime.parse(birthday, DateTimeFormat.forPattern("yyyy-MM-dd"))
                        val pattern1 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

                        val dtf: DateTimeFormatter = DateTimeFormat.forPattern(pattern1)
                        val dateTime: DateTime =
                            dtf.parseDateTime(pattern.toString()) //2000-07-23T00:00:00.000Z

                        val start = DateTime(
                            dateTime.year,
                            dateTime.monthOfYear,
                            dateTime.dayOfWeek,
                            0,
                            0,
                            0,
                            0
                        )
                        val endDate = DateTime.now()

                        val period = Period(start, endDate)

                        date = period.years.toString()
                    } finally {
                    }
                }
                return date
            }
        }
    }

    fun format(): User {
        return User(
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

