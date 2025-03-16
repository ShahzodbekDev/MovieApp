package shahzoddev.mobile.moviesapp

import android.content.Context
import androidx.core.content.edit

class PreferencesHelper(context: Context) {

    private val sharedPref = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    fun setOnBoardingCompleted(completed: Boolean) {
        sharedPref.edit() { putBoolean("onBoarding", completed) }
    }

    fun isOnBoardingCompleted(): Boolean {
        return sharedPref.getBoolean("onBoarding", false)
    }

    fun setOnLoginCompleted(completed: Boolean) {
        sharedPref.edit() { putBoolean("isLogin", completed) }
    }

    fun isOnLoginCompleted(): Boolean {
        return sharedPref.getBoolean("isLogin", false)
    }

    fun setUserName(userName: String) {
        sharedPref.edit() { putString("userName", userName) }
    }

    fun getUserName(): String {
        return sharedPref.getString("userName", "") ?: ""
    }

    fun setEmail(email: String) {
        sharedPref.edit() { putString("email", email) }
    }

    fun getEmail(): String {
        return sharedPref.getString("email", "") ?: ""
    }

    fun setPassword(password: String) {
        sharedPref.edit() { putString("password", password) }
    }

    fun getPassword(): String {
        return sharedPref.getString("password", "") ?: ""
    }

    fun setPhone(phone: String) {
        sharedPref.edit() { putString("phone", phone) }
    }

    fun getPhone(): String {
        return sharedPref.getString("phone", "") ?: ""
    }

    fun setPickAvatar(avatarId: Int) {
        sharedPref.edit() { putInt("pickAvatar", avatarId) }
    }

    fun getPickAvatar(): Int {
        return sharedPref.getInt("pickAvatar", 1) // Default 1
    }

    fun clearAll() {
        sharedPref.edit() { clear() }
    }
}
