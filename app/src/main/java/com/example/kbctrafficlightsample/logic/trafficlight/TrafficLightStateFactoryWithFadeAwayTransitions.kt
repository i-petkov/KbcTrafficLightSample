package com.example.kbctrafficlightsample.logic.trafficlight

import androidx.compose.ui.graphics.lerp
import com.example.kbctrafficlightsample.data.TRAFFIC_LIGHT_FULL_CYCLE_LENGTH
import com.example.kbctrafficlightsample.data.TrafficLightState
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactory.Companion.activeGreen
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactory.Companion.activeRed
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactory.Companion.activeYellow
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedInactive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightOrangeActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightOrangeInactive

class TrafficLightStateFactoryWithFadeAwayTransitions: TrafficLightStateFactory {
    companion object {
        val transitionRedOrange = TrafficLightState(
            lerp(TrafficLightRedActive, TrafficLightRedInactive, 0.5F),
            TrafficLightOrangeActive,
            TrafficLightGreenInactive
        )
        val transitionOrangeGreen = TrafficLightState(
            TrafficLightRedInactive,
            lerp(TrafficLightOrangeActive, TrafficLightOrangeInactive, 0.5F),
            TrafficLightGreenActive
        )
        val transitionGreenRed = TrafficLightState(
            TrafficLightRedActive,
            TrafficLightOrangeInactive,
            lerp(TrafficLightGreenActive, TrafficLightGreenInactive, 0.5F)
        )
    }

    override fun trafficLightStateFor(time: Long): TrafficLightState {
        return when(time % TRAFFIC_LIGHT_FULL_CYCLE_LENGTH) {
            in 0 .. 99 -> transitionGreenRed
            in 100..4000 -> activeRed
            in 4001..4099 -> transitionRedOrange
            in 4100 .. 8000 -> activeYellow
            in 8001 .. 8099 -> transitionOrangeGreen
            else -> activeGreen
        }
    }
}