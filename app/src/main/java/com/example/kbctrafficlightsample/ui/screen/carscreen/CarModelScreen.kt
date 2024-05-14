package com.example.kbctrafficlightsample.ui.screen.carscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CarModelScreen(
    onCarModelConfirmed: (String)->Unit,
    viewModel: CarModelScreenInterface
) {
    Column(
        modifier = Modifier.height(IntrinsicSize.Min).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val carModelValue = remember { mutableStateOf("") }
        val isValidCarModel = viewModel.isCarModelValid(carModelValue.value)

        Text(
            modifier = Modifier.padding(6.dp),
            text = "Please enter your car model name"
        )
        TextField(
            modifier = Modifier.padding(6.dp),
            value = carModelValue.value,
            onValueChange = { carModelValue.value = it },
            isError = !isValidCarModel,
            singleLine = true,
            maxLines = 1
        )

        if (!isValidCarModel) {
            Text(
                modifier = Modifier.padding(6.dp),
                text = "Car Model must be at least 3 characters long"
            )
        }

        Button(
            modifier = Modifier.padding(6.dp),
            enabled = isValidCarModel,
            onClick = { onCarModelConfirmed(carModelValue.value) }
        ) {
            Text(text = "Start Driving")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarModelScreenPreview() {
    CarModelScreen({ /* no-op */ }, object : CarModelScreenInterface {
        override fun isCarModelValid(value: String): Boolean = true
    })
}