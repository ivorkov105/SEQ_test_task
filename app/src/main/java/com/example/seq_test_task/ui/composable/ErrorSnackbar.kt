package com.example.seq_test_task.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.seq_test_task.ui.theme.SeqTestTaskTheme

@Composable
fun ErrorSnackbar(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF333333))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = Color.White,
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = "ПОВТОРИТЬ",
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable { onRetry() }
                .padding(start = 16.dp)
        )
    }
}

@Preview
@Composable
fun ErrorSnackbarPreview() {
    SeqTestTaskTheme {
        ErrorSnackbar(message = "Ошибка подключения сети", onRetry = {})
    }
}