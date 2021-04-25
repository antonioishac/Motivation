package br.com.estudo.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPrefences(context: Context) {

    private val mShsredPreferences: SharedPreferences = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        this.mShsredPreferences.edit().putString(key, value).apply()
    }

    fun getStoreString(key: String) : String {
        return this.mShsredPreferences.getString(key, "") ?: ""
    }
}