package com.example.wordapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.ui.navigation.InventoryNavHost


@Composable
fun WordApp(navController: NavHostController = rememberNavController()) {
    InventoryNavHost(navController = navController)
}