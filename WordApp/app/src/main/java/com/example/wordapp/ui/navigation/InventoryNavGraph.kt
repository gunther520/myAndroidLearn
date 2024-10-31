
package com.example.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.wordapp.ui.home.HomeDestination
import com.example.wordapp.ui.home.HomeScreen
import com.example.wordapp.ui.item.WordDeleteDestination
import com.example.wordapp.ui.item.WordDeleteScreen
import com.example.wordapp.ui.item.WordEntryDestination
import com.example.wordapp.ui.item.WordEntryScreen


/**
 * Provides Navigation graph for the application.
 */
@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {

        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(WordEntryDestination.route) },
                navigateToItemDelete = {
                    navController.navigate(WordDeleteDestination.route)
                }
            )
        }


        composable(route = WordEntryDestination.route) {
            WordEntryScreen(
                onCancel = { navController.navigateUp() }
            )
        }

        composable(route = WordDeleteDestination.route) {
            WordDeleteScreen(
                onCancel = { navController.navigateUp() }
            )
        }

    }
}