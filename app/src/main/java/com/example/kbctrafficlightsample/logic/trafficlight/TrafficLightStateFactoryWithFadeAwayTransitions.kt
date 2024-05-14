package com.example.kbctrafficlightsample.logic.trafficlight

import androidx.compose.ui.graphics.compositeOver
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
import com.example.kbctrafficlightsample.ui.theme.TrafficLightYellowActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightYellowInactive

class TrafficLightStateFactoryWithFadeAwayTransitions: TrafficLightStateFactory {
    companion object {
        val transitionRedYellow = TrafficLightState(
//            TrafficLightRedActive.compositeOver(TrafficLightRedInactive),
            lerp(TrafficLightRedActive, TrafficLightRedInactive, 0.5F),
            TrafficLightYellowActive,
            TrafficLightGreenInactive
        )
        val transitionYellowGreen = TrafficLightState(
            TrafficLightRedInactive,
//            TrafficLightYellowActive.compositeOver(TrafficLightYellowInactive),
            lerp(TrafficLightYellowActive, TrafficLightYellowInactive, 0.5F),
            TrafficLightGreenActive
        )
        val transitionGreenRed = TrafficLightState(
            TrafficLightRedActive,
            TrafficLightYellowInactive,
//            TrafficLightGreenActive.compositeOver(TrafficLightGreenInactive),
            lerp(TrafficLightGreenActive, TrafficLightGreenInactive, 0.5F)
        )
    }

    override fun trafficLightStateFor(time: Long): TrafficLightState {
        return when(time % TRAFFIC_LIGHT_FULL_CYCLE_LENGTH) {
            in 0 .. 99 -> transitionGreenRed
            in 100..3999 -> activeRed
            in 4000..4099 -> transitionRedYellow
            in 4100 .. 7999 -> activeYellow
            in 8000 .. 8099 -> transitionYellowGreen
            else -> activeGreen
        }
    }
}