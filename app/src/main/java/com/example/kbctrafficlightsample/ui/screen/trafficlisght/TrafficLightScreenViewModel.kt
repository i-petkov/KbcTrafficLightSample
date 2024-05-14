package com.example.kbctrafficlightsample.ui.screen.trafficlisght

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.kbctrafficlightsample.data.TRAFFIC_LIGHT_FULL_CYCLE_LENGTH
import com.example.kbctrafficlightsample.data.TrafficLightState
import com.example.kbctrafficlightsample.data.TRAFFIC_LIGHT_UPDATE_DELAY
import com.example.kbctrafficlightsample.logic.configuration.AppLogicConfigurationProvider
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactory
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactoryWithFadeAwayTransitions
import com.example.kbctrafficlightsample.logic.trafficlight.TrafficLightStateFactoryWithSnappyTransitions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class TrafficLightScreenViewModel(private val trafficLightStateFactory: TrafficLightStateFactory) : ViewModel(), TrafficLightScreenInterface {

    companion object {
        private const val UPDATE_CHUNKS = TRAFFIC_LIGHT_FULL_CYCLE_LENGTH / TRAFFIC_LIGHT_UPDATE_DELAY

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])

                val useSmoothLightsTransition = (application as? AppLogicConfigurationProvider)
                    ?.getAppConfiguration()
                    ?.useSmoothLightsTransition
                    ?: false

                val trafficLightStateFactory = if(useSmoothLightsTransition) {
                    TrafficLightStateFactoryWithFadeAwayTransitions()
                } else {
                    TrafficLightStateFactoryWithSnappyTransitions()
                }

                return TrafficLightScreenViewModel(trafficLightStateFactory) as T
            }
        }
    }

    override val trafficLightState: StateFlow<TrafficLightState> = flow {
        while (true) {
            for (chunk in 0 until UPDATE_CHUNKS) {
                // coroutines delay will suspend for "AT LEAST" number of milliseconds
                // however there will always be some extra delay due to context switching
                // that is why we are inventing our own time counter here and not relying on system clock
                val frame = chunk * TRAFFIC_LIGHT_UPDATE_DELAY
                val state = trafficLightStateFactory.trafficLightStateFor(frame)
                emit(state)
                delay(TRAFFIC_LIGHT_UPDATE_DELAY)
            }
        }
    }
        .distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TrafficLightState.Default)
}