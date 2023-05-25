package com.example.exchangerates.presentation.screen.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.exchangerates.R
import com.example.exchangerates.presentation.screen.converter.components.CurrencyTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen(
    navController: NavController,
    viewModel: ConverterViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()
    Column {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.conversion_to, screenState.currency))
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, "Navigate back")
                }
            }
        )
        Text(
            text = "1 RUB = ${screenState.rate} ${screenState.currency}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )
        CurrencyTextField(
            value = screenState.rubAmount,
            onValueChange = { viewModel.rubAmountChanged(it) },
            currencyCode = "RUB",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )
        CurrencyTextField(
            value = screenState.currencyAmount,
            onValueChange = { viewModel.currencyAmountChanged(it) },
            currencyCode = screenState.currency,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )
    }
}