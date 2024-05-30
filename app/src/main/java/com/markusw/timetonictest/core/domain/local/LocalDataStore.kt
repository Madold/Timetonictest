package com.markusw.timetonictest.core.domain.local

interface LocalDataStore {
    fun saveData(data: String, key: String)
    fun getData(key: String, defaultValue: String): String
}