package com.example.kbctrafficlightsample.ui.screen.carscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.kbctrafficlightsample.data.configuration.AppLogicConfigurationProvider
import com.example.kbctrafficlightsample.logic.carmodelvalidator.CarModelValidator
import com.example.kbctrafficlightsample.logic.carmodelvalidator.ModelNameLengthCarModelValidator

class CarModelScreenViewModel(private val validator: CarModelValidator) : ViewModel(), CarModelScreenInterface {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                val carModelMinimumLength = (application as? AppLogicConfigurationProvider)
                    ?.getAppConfiguration()
                    ?.carModelMinimumLength
                    ?: 3

                return CarModelScreenViewModel(ModelNameLengthCarModelValidator(carModelMinimumLength)) as T
            }
        }
    }
    override fun isCarModelValid(value: String): Boolean = validator.validateModel(value)
}