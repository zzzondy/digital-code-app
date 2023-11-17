package com.digitalcodeapp.screens.dictionary.presentation.states.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.digitalcodeapp.R
import com.digitalcodeapp.common.utils.items
import com.digitalcodeapp.screens.dictionary.domain.models.DictionaryTerm
import com.digitalcodeapp.common.ui.theme.DigitalCodeAppTheme

@Composable
fun DictionaryScreenContentState(
    dictionaryTerms: LazyPagingItems<DictionaryTerm>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(dictionaryTerms, key = { it.id }) { term ->
            DictionaryItem(
                label = term.label,
                description = term.description,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item {
            dictionaryTerms.apply {
                when (loadState.append) {
                    is LoadState.Error -> {
                        ErrorWhenAppend(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }

                    is LoadState.Loading -> {
                        AppendLoadingState(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}

@Composable
private fun ErrorWhenAppend(modifier: Modifier = Modifier, onRefresh: () -> Unit = {}) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.refresh_error),
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRefresh) {
            Text(text = stringResource(R.string.refresh))
        }
    }
}

@Composable
private fun AppendLoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DictionaryItem(
    label: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    val arrowRotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "rotation",
        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
    )

    Card(
        onClick = { expanded = !expanded },
        modifier = modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 100,
                    easing = LinearEasing
                )
            )
            .heightIn(max = 200.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                )

                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .rotate(arrowRotation)
                ) {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription = null)
                }
            }

            if (expanded) {
                LazyColumn {
                    item {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DictionaryItemPreview() {
    DigitalCodeAppTheme {
        DictionaryItem(
            label = "Государство",
            description = "dfksdkfjgksdjfgksdjgksjflsjgskdjfglsdjfkgjsdfgjsdfgjskljfkg",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

    }
}