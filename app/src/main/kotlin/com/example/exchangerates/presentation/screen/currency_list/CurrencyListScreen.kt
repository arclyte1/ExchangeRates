package com.example.exchangerates.presentation.screen.currency_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.exchangerates.R
import com.example.exchangerates.presentation.Screen
import com.example.exchangerates.presentation.screen.currency_list.components.ExchangeRateCard
import com.example.exchangerates.presentation.screen.currency_list.components.SearchField

@Composable
fun CurrencyListScreen(
    navController: NavController,
    viewModel: CurrencyListViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()
    Column {
        SearchField(
            textChanged = viewModel::searchTextChanged,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(40.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 16.dp)
        ) {
            items(screenState.exchangeRates) { item ->
                ExchangeRateCard(
                    currency = item.currency,
                    rate = item.formattedRate,
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            navController.navigate(
                                Screen.ConverterScreen.route + "/${item.currency}/${item.fullRate}"
                            )
                        }
                )
            }
        }
    }

    if (screenState.isLoading)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

    if (screenState.isError)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.getting_exchange_rates_error),
                    color = Color.Gray,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    modifier = Modifier.padding(32.dp)
                )
                Button(
                    onClick = { viewModel.getLatestExchangeRates() }
                ) {
                    Text(text = stringResource(id = R.string.try_again))
                }
            }
        }
    
    if (screenState.exchangeRates.isEmpty() && !screenState.isLoading && !screenState.isError)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.nothing_found),
                color = Color.Gray,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(32.dp)
            )
        }
}