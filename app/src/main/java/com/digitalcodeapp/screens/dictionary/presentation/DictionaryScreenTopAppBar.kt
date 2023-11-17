package com.digitalcodeapp.screens.dictionary.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.digitalcodeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreenTopAppBar(onBackButtonClicked: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.dictionary))
        },
        navigationIcon = {
            IconButton(
                onClick = onBackButtonClicked,
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.return_back)
                )
            }
        }
    )

}