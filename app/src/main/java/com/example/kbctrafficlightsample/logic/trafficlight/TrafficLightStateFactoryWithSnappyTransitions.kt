package com.example.kbctrafficlightsample.logic.trafficlight

import com.example.kbctrafficlightsample.data.TRAFFIC_LIGHT_FULL_CYCLE_LENGTH
import com.example.kbctrafficlightsample.data.TrafficLightState
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactory.Companion.activeGreen
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactory.Companion.activeRed
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactory.Companion.activeYellow

class TrafficLightStateFactoryWithSnappyTransitions: TrafficLightStateFactory {

    override fun trafficLightStateFor(time: Long): TrafficLightState {
        return when(time% TRAFFIC_LIGHT_FULL_CYCLE_LENGTH) {
            in 0..4000 -> activeRed
            in 4001 .. 8000 -> activeYellow
            else -> activeGreen
        }
    }
}