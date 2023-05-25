package com.example.exchangerates.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exchangerates.presentation.screen.converter.ConverterScreen
import com.example.exchangerates.presentation.screen.currency_list.CurrencyListScreen
import com.example.exchangerates.presentation.ui.theme.ExchangeRatesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRatesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CurrencyListScreen.route
                    ) {
                        composable(
                            route = Screen.CurrencyListScreen.route
                        ) {
                            CurrencyListScreen(navController)
                        }
                        composable(
                            route = Screen.ConverterScreen.route + "/{currency}/{rate}",
                            arguments = listOf(
                                navArgument("currency") { type = NavType.StringType },
                                navArgument("rate") { type = NavType.StringType }
                            )
                        ) {
                            ConverterScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
