package com.example.kbctrafficlightsample.logic.trafficlight

import com.example.kbctrafficlightsample.data.TrafficLightState
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightYellowActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightYellowInactive

interface TrafficLightStateFactory {
    companion object {
        val activeRed = TrafficLightState(
            TrafficLightRedActive,
            TrafficLightYellowInactive,
            TrafficLightGreenInactive
        )
        val activeYellow = TrafficLightState(
            TrafficLightRedInactive,
            TrafficLightYellowActive,
            TrafficLightGreenInactive
        )

        val activeGreen = TrafficLightState(
            TrafficLightRedInactive,
            TrafficLightYellowInactive,
            TrafficLightGreenActive
        )
    }
    fun trafficLightStateFor(time: Long): TrafficLightState
}