package com.example.kbctrafficlightsample.data

import androidx.compose.ui.graphics.Color
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightOrangeInactive

data class TrafficLightState(
    val red: Color,
    val orange: Color,
    val green: Color
) {
    companion object {
        val Default = TrafficLightState(
            TrafficLightRedInactive,
            TrafficLightOrangeInactive,
            TrafficLightGreenInactive
        )
    }
}