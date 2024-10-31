package com.example.wordapp.ui.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordapp.R
import com.example.wordapp.ui.AppViewModelProvider
import com.example.wordapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object WordDeleteDestination : NavigationDestination {
    override val route = "delete"
    override val titleRes = R.string.delete_word
}

@Composable
fun WordDeleteScreen(
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WordDeleteViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope = rememberCoroutineScope()
    val wordEntryUiState= viewModel.wordDeleteUiState

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = wordEntryUiState.itemDetails.word,
            onValueChange = { viewModel.updateUiState(it) },
            label = { Text(text = stringResource(R.string.delete_word)) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.deleteWord()
                    onCancel()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Enter Word to Delete")
        }
        Button(
            onClick = onCancel,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = "Cancel")
        }
    }
}