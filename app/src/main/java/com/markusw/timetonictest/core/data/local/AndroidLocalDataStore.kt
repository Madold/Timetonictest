package com.markusw.timetonictest.core.data.local

import android.content.Context
import com.markusw.timetonictest.core.domain.local.LocalDataStore

class AndroidLocalDataStore(context: Context): LocalDataStore {

    private val sharedPreferences = context
        .getSharedPreferences("local_data", Context.MODE_PRIVATE)
    override fun saveData(data: String, key: String) {
        return sharedPreferences
            .edit()
            .putString(key, data)
            .apply()
    }

    override fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }


}