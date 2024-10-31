package com.example.wordapp.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordapp.data.WordRepository
import kotlinx.coroutines.launch

class WordDeleteViewModel(private val wordRepository: WordRepository) : ViewModel() {

    var wordDeleteUiState by mutableStateOf(WordDeleteUiState())
        private set

    fun updateUiState(word: String) {
        wordDeleteUiState = wordDeleteUiState.copy(
            itemDetails = wordDeleteUiState.itemDetails.copy(
                word = word,
            )
        )
    }

    fun deleteWord() {
        viewModelScope.launch {
            wordRepository.deleteWord(wordDeleteUiState.itemDetails.word)
        }
    }


}

data class WordDeleteUiState(
    val itemDetails: WordDetails = WordDetails(),
)