package com.example.kbctrafficlightsample

import android.app.Application
import com.example.kbctrafficlightsample.data.configuration.AppConfiguration
import com.example.kbctrafficlightsample.data.configuration.AppLogicConfigurationProvider

class KbcTrafficLightSampleApplication: Application(), AppLogicConfigurationProvider {
    private val appConfiguration = AppConfiguration(false)

    override fun getAppConfiguration(): AppConfiguration = appConfiguration
}