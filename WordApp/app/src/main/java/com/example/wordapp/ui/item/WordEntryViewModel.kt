package com.example.wordapp.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wordapp.data.Word
import com.example.wordapp.data.WordRepository

/**
 * ViewModel to validate and insert items in the Room database.
 */
class WordEntryViewModel(private val wordRepository: WordRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var wordEntryUiState by mutableStateOf(WordEntryUiState())
        private set

    /**
     * Updates the [wordEntryUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(word: String) {
        wordEntryUiState = wordEntryUiState.copy(
            itemDetails = wordEntryUiState.itemDetails.copy(
                word = word,
            )
        )
    }

    /**
     * Inserts an [Item] in the Room database
     */
    suspend fun saveItem() {
        wordRepository.insertWord(wordEntryUiState.itemDetails.toWord())
    }


}

/**
 * Represents Ui State for an Item.
 */
data class WordEntryUiState(
    val itemDetails: WordDetails= WordDetails(),
)

data class WordDetails(
    val id: Int = 0,
    val word: String = "",
)


/**
 * Extension function to convert [WordUiState] to [Word]. If the value of [WordDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [WordUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun WordDetails.toWord(): Word = Word(
    id = id,
    word = word,
)



/**
 * Extension function to convert [Word] to [WordUiState]
 */
fun Word.toWordUiState(isEntryValid: Boolean = false): WordEntryUiState = WordEntryUiState(
    itemDetails = WordDetails(
        id = id,
        word = word,
    ),
)

/**
 * Extension function to convert [Word] to [WordDetails]
 */
fun Word.toWordDetails(): WordDetails = WordDetails(
    id= id,
    word = word,
)