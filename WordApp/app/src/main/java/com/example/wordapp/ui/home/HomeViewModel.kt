package com.example.wordapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordapp.data.Word
import com.example.wordapp.data.WordRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel(private val wordRepository: WordRepository) : ViewModel() {

    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     */
    val homeUiState: StateFlow<HomeUiState> =
        wordRepository.getAllWordsStream().map{ HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun deleteAllWords() {
        viewModelScope.launch {
            wordRepository.deleteAll()
        }
    }




    companion object {
        private const val TIMEOUT_MILLIS = 5_000L


    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Word> = listOf())