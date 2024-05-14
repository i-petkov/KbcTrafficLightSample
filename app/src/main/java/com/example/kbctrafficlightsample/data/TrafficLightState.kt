package com.example.kbctrafficlightsample.data

import androidx.compose.ui.graphics.Color
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightYellowInactive

data class TrafficLightState(
    val red: Color,
    val yellow: Color,
    val green: Color
) {
    companion object {
        val Default = TrafficLightState(
            TrafficLightRedInactive,
            TrafficLightYellowInactive,
            TrafficLightGreenInactive
        )
    }
}