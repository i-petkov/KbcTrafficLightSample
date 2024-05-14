package com.example.kbctrafficlightsample.ui.screen.trafficlisght

import com.example.kbctrafficlightsample.data.TrafficLightState
import kotlinx.coroutines.flow.StateFlow

interface TrafficLightScreenInterface {
    val trafficLightState: StateFlow<TrafficLightState>
}