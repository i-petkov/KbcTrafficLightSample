package com.example.kbctrafficlightsample.logic.carmodelvalidator

import org.junit.Assert.*

import org.junit.Test
import kotlin.text.Typography.nbsp

class ModelNameLengthCarModelValidatorTest {

    private val minimumLength = 3
    private val validator = ModelNameLengthCarModelValidator(minimumLength)

    private val cases = mapOf(
        "AAA" to true,
        "AAABBB" to true,
        "AA" to false,
        "AA " to false,
        " AA " to false,
        " AA" to false,
        "\nAA" to false,
        "\nAA\n" to false,
        "AA\n" to false,
        "\tAA" to false,
        "\tAA\t" to false,
        "AA\t" to false,
        "$nbsp" to false,
        "${nbsp}AA" to false,
        "${nbsp}AA${nbsp}" to false,
        "AA${nbsp}" to false,
    )

    @Test
    fun validateModel() {
        for (case in cases) {
            val model = case.key
            val expected = case.value
            val actual = validator.validateModel(model)
            assert(expected == actual) {
                "model: $model, expected: $expected, actual: $actual"
            }
        }
    }
}