package com.example.letssopt.data.local

import android.content.Context

class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveSignUpInfo(userId: String, userPw: String) {
        prefs.edit()
            .putString(KEY_USER_ID, userId)
            .putString(KEY_USER_PW, userPw)
            .apply()
    }

    fun saveUserProfile(name: String, email: String, age: Int, part: String) {
        prefs.edit()
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email)
            .putInt(KEY_AGE, age)
            .putString(KEY_PART, part)
            .apply()
    }

    fun saveLoginState(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun getUserId(): String? = prefs.getString(KEY_USER_ID, null)
    fun getUserPw(): String? = prefs.getString(KEY_USER_PW, null)
    fun getName(): String? = prefs.getString(KEY_NAME, null)
    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)
    fun getAge(): Int = prefs.getInt(KEY_AGE, 0)
    fun getPart(): String? = prefs.getString(KEY_PART, null)
    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun clear() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "user_info"
        private const val KEY_USER_ID = "userId"
        private const val KEY_USER_PW = "userPw"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_AGE = "age"
        private const val KEY_PART = "part"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }
}
