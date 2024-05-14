package com.example.kbctrafficlightsample.ui.screen.carscreen

import androidx.lifecycle.ViewModel

class CarModelScreenViewModel : ViewModel(), CarModelScreenInterface {
    override fun isCarModelValid(value: String): Boolean = value.trim().length > 3
}