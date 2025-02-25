package shahzoddev.mobile.moviesapp.sqlitedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, "my_db", null, 1) {

    companion object {

        const val tableName = "users"
        const val id = "_id"
        const val onBoarding = "onBoarding"
        const val UserName = "userName"
        const val Email = "email"
        const val Password = "password"
        const val Phone = "phone"
        const val Avatar = "avatar"
        const val watchList = "watchList"
        const val history = "history"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE $tableName (" +
                    "$id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$onBoarding INTEGER, " +
                    "$UserName TEXT, " +
                    "$Email TEXT, " +
                    "$Password TEXT, " +
                    "$Phone INTEGER, " +
                    "$Avatar INTEGER, " +
                    "$watchList TEXT, " +
                    "$history TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)

    }
}