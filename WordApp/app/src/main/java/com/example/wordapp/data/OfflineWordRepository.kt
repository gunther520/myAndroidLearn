package com.example.wordapp.data

import kotlinx.coroutines.flow.Flow

class OfflineWordsRepository(private val wordDao: WordDao) : WordRepository {
    override fun getAllWordsStream(): Flow<List<Word>> = wordDao.getAlphabetizedWords()


    override suspend fun insertWord(word: Word) = wordDao.insert(word)

    override suspend fun deleteWord(word: String) = wordDao.deleteWord(word)

    override suspend fun deleteAll() = wordDao.deleteAll()


}
