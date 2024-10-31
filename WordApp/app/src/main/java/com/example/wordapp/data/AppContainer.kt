package com.example.wordapp.data

import android.content.Context

interface AppContainer {
    val wordRepository: WordRepository
}


class AppDataContainer(private val context: Context) : AppContainer {
    override val wordRepository: WordRepository by lazy {
        OfflineWordsRepository(WordRoomDatabase.getDatabase(context).wordDao())
    }
}
