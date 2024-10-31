package com.example.wordapp.data

import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getAllWordsStream(): Flow<List<Word>>

    suspend fun insertWord(word: Word)


    suspend fun deleteWord(word: String)

    suspend fun deleteAll()


}