package shahzoddev.mobile.moviesapp.sqlitedatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbManager(private val context: Context) {

    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var sqLiteDatabase: SQLiteDatabase

    fun onCreate() {
        sqLiteHelper = SQLiteHelper(context)
        sqLiteDatabase = sqLiteHelper.writableDatabase
    }

    fun insertUser(dbModel: DbModel) {
        val cv = sqLiteDatabase.insert(SQLiteHelper.tableName, null, ContentValues().apply {
            put(SQLiteHelper.UserName, dbModel.userName)
            put(SQLiteHelper.Email, dbModel.email)
            put(SQLiteHelper.Password, dbModel.password)
            put(SQLiteHelper.Phone, dbModel.phone)
            put(SQLiteHelper.Avatar, dbModel.avatar)
            put(SQLiteHelper.watchList, dbModel.watchList)
            put(SQLiteHelper.history, dbModel.history)
        })
    }

    @SuppressLint("Recycle")
    fun fetch(): Cursor? {
        val cursor = sqLiteDatabase.query(
            SQLiteHelper.tableName,
            arrayOf(
                SQLiteHelper.id,
                SQLiteHelper.UserName,
                SQLiteHelper.Email,
                SQLiteHelper.Password,
                SQLiteHelper.Phone,
                SQLiteHelper.Avatar,
                SQLiteHelper.watchList,
                SQLiteHelper.history

            ),
            null,
            null,
            null,
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {

            cursor

        } else {
            null
        }
    }

    fun registerUser(dbModel: DbModel): Int {
        val contentValues = ContentValues()
        contentValues.put(SQLiteHelper.UserName, dbModel.userName)
        contentValues.put(SQLiteHelper.Email, dbModel.email)
        contentValues.put(SQLiteHelper.Password, dbModel.password)
        contentValues.put(SQLiteHelper.Phone, dbModel.phone)

        return sqLiteDatabase.update(
            SQLiteHelper.tableName,
            contentValues,
            null,
            null
        )
    }

    fun updateOnBoarding(onBoarding: Int) :Int {
        return sqLiteDatabase.update(
            SQLiteHelper.tableName,
            ContentValues().apply { put(SQLiteHelper.onBoarding, onBoarding) },
            null,
            null
        )
    }

}