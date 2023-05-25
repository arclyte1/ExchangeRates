package com.example.exchangerates.presentation.screen.currency_list.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exchangerates.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    textChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            textChanged(it)
        },
        textStyle = TextStyle(
            fontSize = 16.sp
        ),
        modifier = modifier,
        singleLine = true,
        cursorBrush = SolidColor(Unspecified)
    ) { innerTextField ->
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = text,
            visualTransformation = VisualTransformation.None,
            innerTextField = innerTextField,
            singleLine = true,
            enabled = true,
            interactionSource = MutableInteractionSource(),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon",
                    tint = Color.Gray
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_by_currency_code),
                    color = Color.Gray
                )
            }
        )
    }
}

@Composable
@Preview
fun SearchFieldPreview() {
    SearchField(
        textChanged = {},
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(40.dp)
    )
}