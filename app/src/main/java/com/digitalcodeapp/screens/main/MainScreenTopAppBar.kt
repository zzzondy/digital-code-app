package com.digitalcodeapp.screens.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.digitalcodeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopAppBar() {
    MediumTopAppBar(title = { Text(text = stringResource(R.string.main_screen)) })
}