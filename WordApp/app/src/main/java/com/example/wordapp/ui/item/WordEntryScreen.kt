package com.example.wordapp.ui.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordapp.R
import com.example.wordapp.ui.AppViewModelProvider
import com.example.wordapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch


object WordEntryDestination : NavigationDestination {
    override val route = "entry"
    override val titleRes = R.string.new_word
}

@Composable
fun WordEntryScreen(
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WordEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val wordEntryUiState= viewModel.wordEntryUiState

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = wordEntryUiState.itemDetails.word,
            onValueChange = { viewModel.updateUiState(it) },
            label = { Text(text = "Enter word to add") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    onCancel()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Confirm")
        }
        Button(
            onClick = onCancel,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Cancel")
        }
    }
}