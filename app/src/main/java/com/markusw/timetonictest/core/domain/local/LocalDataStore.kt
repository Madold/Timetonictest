package com.markusw.timetonictest.core.domain.local

/**
 * Interface for storing data locally
 * Implementations should store data in a way that it persists between app restarts
 */
interface LocalDataStore {
    /**
     * Saves data to the local storage
     * @param data the data to save
     * @param key the key to save the data under
     */
    fun saveData(data: String, key: String)

    /**
     * Gets data from the local storage
     * @param key the key to get the data from
     * @param defaultValue the default value to return if the key is not found
     */
    fun getData(key: String, defaultValue: String): String

    /**
     * Removes data from the local storage
     * @param key the key to remove the data from
     */
    fun remove(key: String)
}