package com.example.todoapplication.domain.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


data class UsersBase(@SerializedName("response") var response: MutableList<User> = mutableListOf())

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,

    @SerializedName("f_name") var firstName: String = "",
    @SerializedName("l_name") var lastName: String = "",
    @SerializedName("birthday") var birthday: String? = "",
    @SerializedName("avatr_url") var avatrUrl: String = ""
) : Parcelable {

    // Преобразование к нижнему регистру. Устанавливает первый символ в верхний регистр
    fun fixFailureFields(string: String): String = (string.toLowerCase()).capitalize()

    // Преобразование даты рождения к единому формату
    fun changeBirtday(birthday: String?): String {
        return when (birthday) {
            null -> "-"
            "" -> "-"
            else -> {
                // TODO: 25.12.20  Даты прилетают неправильные, если не совпадает, то подставляется
                val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
                val formatter2 = DateTimeFormat.forPattern("dd-MM-yyyy")
                var dt: DateTime

                // В связи с разным форматом, пока такое решение
                try {
                    dt = formatter.parseDateTime(birthday)
                } catch (e: Exception) {
                    try {
                        dt = formatter2.parseDateTime(birthday)
                    } finally {
                    }
                }
                return dt.dayOfWeek.toString() + "." + dt.monthOfYear
                    .toString() + "." + dt.year.toString() + " г."
            }
        }
    }

    fun format(): User {
        return User(
            id,
            fixFailureFields(firstName),
            fixFailureFields(lastName),
            changeBirtday(birthday),
            avatrUrl
        )
    }
}

@Parcelize
@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )]
)

data class Specialty(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,

    @SerializedName("specialty_id") var specialtyId: Int = 0,
    @SerializedName("name") var name: String = "",
    @ColumnInfo(name = "userId")
    var userId: Long = 0
) : Parcelable


//@Entity(tableName = "my_table")
//class MyData {
//    @PrimaryKey(autoGenerate = true)
//    var id = 0
//
//    @ColumnInfo(name = "ListData")
//    @TypeConverters(DataTypeConverter::class)
//    private var mList: List<MyListObject>? = null
//
//    @Embedded
//    var user: Userd? = null
//    var list: List<Any>?
//        get() = mList
//        set(list) {
//            mList = list
//        }
//}
//
//class Userd {
//    @ColumnInfo(name = "first_name")
//    var firstName: String? = null
//
//    @ColumnInfo(name = "last_name")
//    var lastName: String? = null
//}
//
//object DataTypeConverter {
//    private val gson = Gson()
//    @TypeConverter
//    fun stringToList(data: String?): List<MyListObject> {
//        if (data == null) {
//            return Collections.emptyList()
//        }
//        val listType: Type = object : TypeToken<List<MyListObject?>?>() {}.type
//        return gson.fromJson<List<MyListObject>>(data, listType)
//    }
//
//    @TypeConverter
//    fun ListToString(someObjects: List<MyListObject?>?): String {
//        return gson.toJson(someObjects)
//    }
//}


/*Вариант на json с specialty: String = ""
*
*     @Insert
    fun insertAll(user: List<User>): LongArray?
* */
//data class UsersBase(@SerializedName("response") var response: List<User> = listOf())
//
//@Parcelize
//@Entity
//data class User(
//    @PrimaryKey(autoGenerate = true) var id: Long = 0,
//
//    @SerializedName("f_name") var firstName: String = "",
//    @SerializedName("l_name") var lastName: String = "",
//    @SerializedName("birthday") var birthday: String? = "",
//    @SerializedName("avatr_url") var avatrUrl: String = "",
//
//
//    @SerializedName("specialty") var specialty: String = "",
//    var specialtyId: Long = 0
//) : Parcelable
//
//@Parcelize
//@Entity(
//    foreignKeys = [ForeignKey(
//        entity = User::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("userId"),
//        onDelete = ForeignKey.CASCADE
//    )]
//)
//data class Specialty(
//    @PrimaryKey(autoGenerate = true) var id: Long = 0,
//
//    @SerializedName("specialty_id") var specialtyId: Int = 0,
//    @SerializedName("name") var name: String = "",
//    @ColumnInfo(name = "userId")
//    var userId: Long = 0
//) : Parcelable
