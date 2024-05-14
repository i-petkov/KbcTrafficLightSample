package com.example.kbctrafficlightsample.logic.trafficlight

import com.example.kbctrafficlightsample.data.TRAFFIC_LIGHT_FULL_CYCLE_LENGTH
import com.example.kbctrafficlightsample.ui.theme.TrafficLightGreenActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightOrangeActive
import com.example.kbctrafficlightsample.ui.theme.TrafficLightRedActive

import org.junit.Test

class TrafficLightStateFactoryWithFadeAwayTransitionsTest {

    private val factory = TrafficLightStateFactoryWithFadeAwayTransitions()

    @Test
    fun trafficLightState_ShouldHaveActiveRed_ForTheFirstFourSecond() {
        val activeFrameStart = 0L
        val activeFrameEnd = activeFrameStart + 4000L // 4 seconds

        for (frame in 0 until TRAFFIC_LIGHT_FULL_CYCLE_LENGTH step 100) {
            val state = factory.trafficLightStateFor(frame)
            val expected = frame in activeFrameStart .. activeFrameEnd
            val actual = state.red == TrafficLightRedActive
            assert(expected == actual) {
                "frame: $frame, expected: $expected, actual: $actual"
            }
        }
    }

    @Test
    fun trafficLightState_ShouldHaveActiveOrange_ForTheSecondFourSecond() {
        val activeFrameStart = 4001L
        val activeFrameEnd = activeFrameStart + 4000L // 4 seconds

        for (frame in 0 until TRAFFIC_LIGHT_FULL_CYCLE_LENGTH step 100) {
            val state = factory.trafficLightStateFor(frame)
            val expected = frame in activeFrameStart .. activeFrameEnd
            val actual = state.orange == TrafficLightOrangeActive
            assert(expected == actual) {
                "frame: $frame, expected: $expected, actual: $actual"
            }
        }
    }

    @Test
    fun trafficLightState_ShouldHaveActiveGreen_ForTheLastOneSecond() {
        val activeFrameStart = 8001L
        val activeFrameEnd = activeFrameStart + 1000L // 1 seconds

        for (frame in 0 until TRAFFIC_LIGHT_FULL_CYCLE_LENGTH step 100) {
            val state = factory.trafficLightStateFor(frame)
            val expected = frame in activeFrameStart .. activeFrameEnd
            val actual = state.green == TrafficLightGreenActive
            assert(expected == actual) {
                "frame: $frame, expected: $expected, actual: $actual"
            }
        }
    }

    @Test
    fun trafficLightState_ShouldHaveFadeAwayGreenToRed_ForTheFirstNinetyNineSecondsOfRedBeingActive() {
        val activeFrameStart = 0L
        val activeFrameEnd = activeFrameStart + 99L

        for (frame in 0 until TRAFFIC_LIGHT_FULL_CYCLE_LENGTH step 100) {
            val state = factory.trafficLightStateFor(frame)
            val expected = frame in activeFrameStart .. activeFrameEnd
            val actual = state == TrafficLightStateFactoryWithFadeAwayTransitions.transitionGreenRed
            assert(expected == actual) {
                "frame: $frame, expected: $expected, actual: $actual"
            }
        }
    }

    @Test
    fun trafficLightState_ShouldHaveFadeAwayRedToOrange_ForTheFirstNinetyEightSecondsOfOrangeBeingActive() {
        val activeFrameStart = 4001L
        val activeFrameEnd = activeFrameStart + 98L

        for (frame in 0 until TRAFFIC_LIGHT_FULL_CYCLE_LENGTH step 100) {
            val state = factory.trafficLightStateFor(frame)
            val expected = frame in activeFrameStart .. activeFrameEnd
            val actual = state == TrafficLightStateFactoryWithFadeAwayTransitions.transitionRedOrange
            assert(expected == actual) {
                "frame: $frame, expected: $expected, actual: $actual"
            }
        }
    }

    @Test
    fun trafficLightState_ShouldHaveFadeAwayOrangeToGreen_ForTheFirstNinetyEightSecondsOfGreenBeingActive() {
        val activeFrameStart = 8001L
        val activeFrameEnd = activeFrameStart + 98L

        for (frame in 0 until TRAFFIC_LIGHT_FULL_CYCLE_LENGTH step 100) {
            val state = factory.trafficLightStateFor(frame)
            val expected = frame in activeFrameStart .. activeFrameEnd
            val actual = state == TrafficLightStateFactoryWithFadeAwayTransitions.transitionOrangeGreen
            assert(expected == actual) {
                "frame: $frame, expected: $expected, actual: $actual"
            }
        }
    }
}