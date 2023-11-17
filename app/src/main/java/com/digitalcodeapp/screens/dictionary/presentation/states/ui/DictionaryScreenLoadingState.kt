package com.digitalcodeapp.screens.dictionary.presentation.states.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digitalcodeapp.common.ui.theme.DigitalCodeAppTheme
import com.digitalcodeapp.common.utils.shimmerEffect

@Composable
fun DictionaryScreenLoadingState(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = false,
    ) {
        items(15) {
            LoadingDictionaryItem(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun LoadingDictionaryItem(modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .height(40.dp)
            .shimmerEffect(true),
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun DictionaryScreenLoadingStatePreview() {
    DigitalCodeAppTheme {
        DictionaryScreenLoadingState(modifier = Modifier.fillMaxSize())
    }
}