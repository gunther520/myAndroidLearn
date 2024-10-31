package com.example.wordapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordapp.R
import com.example.wordapp.data.Word

import com.example.wordapp.ui.AppViewModelProvider
import com.example.wordapp.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemDelete: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = navigateToItemEntry,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .padding(
                            end = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateEndPadding(LocalLayoutDirection.current)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_word)
                    )
                }
                FloatingActionButton(
                    onClick = navigateToItemDelete,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            end = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateEndPadding(LocalLayoutDirection.current)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_word)
                    )
                }
                FloatingActionButton(
                    onClick = { viewModel.deleteAllWords() },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            end = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateEndPadding(LocalLayoutDirection.current)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.delete_all)
                    )
                }
            }
        },
    ) { innerPadding ->
        HomeBody(
            itemList = homeUiState.itemList,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding,
        )
    }
}

@Composable
fun HomeBody(itemList: List<Word>, modifier: Modifier, contentPadding: PaddingValues) {
    if (itemList.isEmpty()) {
        Text(
            text = stringResource(R.string.no_word_yet),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(contentPadding),
        )
    } else {
        WordList(
            wordList = itemList,
            contentPadding = contentPadding,
            modifier = Modifier
        )
    }
}

@Composable
fun WordList(wordList: List<Word>, contentPadding: PaddingValues, modifier: Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = wordList, key = { it.id }) { word ->
            Card(modifier = Modifier.fillMaxSize(),) {
                Text(
                    text = word.word,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
                )

            }
        }
    }
}
