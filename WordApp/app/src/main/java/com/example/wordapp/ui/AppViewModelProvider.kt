package com.example.wordapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wordapp.WordApplication
import com.example.wordapp.ui.home.HomeViewModel
import com.example.wordapp.ui.item.WordDeleteViewModel
import com.example.wordapp.ui.item.WordEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(
                inventoryApplication().container.wordRepository
            )
        }

        initializer {
            WordEntryViewModel(
                inventoryApplication().container.wordRepository
            )
        }

        initializer {
            WordDeleteViewModel(
                inventoryApplication().container.wordRepository
            )
        }



    }
}


fun CreationExtras.inventoryApplication(): WordApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WordApplication)