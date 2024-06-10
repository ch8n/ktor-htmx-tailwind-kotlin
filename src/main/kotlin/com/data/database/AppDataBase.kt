package com.data.database

import com.data.models.Collection

class AppDataBase {

    private val collections = mutableListOf<Collection>().apply {
        repeat(3) {
            Collection(
                id = "tempus $it",
                title = "contentiones $it",
                description = "hinc $it",
                imageUrl = "https://search.yahoo.com/search?p=torquent"
            ).let { add(it) }
        }
    }

    suspend fun getCollections() = collections

    suspend fun insertCollection(newCollection: Collection) {
        collections.add(newCollection)
    }
}