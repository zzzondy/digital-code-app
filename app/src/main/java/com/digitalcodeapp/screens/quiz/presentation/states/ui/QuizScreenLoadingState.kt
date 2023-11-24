package com.digitalcodeapp.screens.quiz.presentation.states.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digitalcodeapp.common.ui.theme.DigitalCodeAppTheme
import com.digitalcodeapp.common.utils.shimmerEffect

@Composable
fun QuizScreenLoadingState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            items(10) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .size(48.dp)
                        .shimmerEffect(true)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .shimmerEffect(true)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .shimmerEffect(true)
        )

        Spacer(modifier = Modifier.height(232.dp))

        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .shimmerEffect(true)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .shimmerEffect(true)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .shimmerEffect(true)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .shimmerEffect(true)
        )

    }
}

@Preview
@Composable
private fun QuizScreenLoadingStatePreview() {
    DigitalCodeAppTheme {
        QuizScreenLoadingState(
            modifier = Modifier.fillMaxSize()
        )
    }
}