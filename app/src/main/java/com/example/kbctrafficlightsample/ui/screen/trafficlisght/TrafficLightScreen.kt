package com.example.kbctrafficlightsample.ui.screen.trafficlisght

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kbctrafficlightsample.data.TrafficLightState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TrafficLightScreen(viewModel: TrafficLightScreenInterface) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .width(100.dp)
            .height(300.dp)
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val trafficLightState = viewModel.trafficLightState.collectAsStateWithLifecycle(initialValue = TrafficLightState.Default)

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .weight(1F)
            .background(trafficLightState.value.red)
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .weight(1F)
            .background(trafficLightState.value.orange)
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .weight(1F)
            .background(trafficLightState.value.green)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrafficLightScreenPreview() {
    TrafficLightScreen(object : TrafficLightScreenInterface {
        override val trafficLightState: StateFlow<TrafficLightState>
            get() = MutableStateFlow(TrafficLightState.Default)
    })
}