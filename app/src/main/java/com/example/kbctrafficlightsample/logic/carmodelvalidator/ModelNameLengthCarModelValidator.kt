package com.example.kbctrafficlightsample.logic.carmodelvalidator

class ModelNameLengthCarModelValidator(private val minLength: Int) : CarModelValidator {
    override fun validateModel(model: String): Boolean = model.trim().length >= minLength
}