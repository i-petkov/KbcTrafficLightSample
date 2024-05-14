package com.example.kbctrafficlightsample.logic.trafficlight

import com.example.kbctrafficlightsample.data.TrafficLightState
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightOrangeActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightOrangeInactive

interface TrafficLightStateFactory {
    companion object {
        val activeRed = TrafficLightState(
            TrafficLightRedActive,
            TrafficLightOrangeInactive,
            TrafficLightGreenInactive
        )
        val activeYellow = TrafficLightState(
            TrafficLightRedInactive,
            TrafficLightOrangeActive,
            TrafficLightGreenInactive
        )

        val activeGreen = TrafficLightState(
            TrafficLightRedInactive,
            TrafficLightOrangeInactive,
            TrafficLightGreenActive
        )
    }
    fun trafficLightStateFor(time: Long): TrafficLightState
}