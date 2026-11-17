package com.example.myhealth.ui.health.setup

import androidx.lifecycle.ViewModel
import com.example.myhealth.core.common.result.toFormattedBmi
import com.example.myhealth.core.model.HealthSetupStep
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow

@HiltViewModel
class HealthSetupViewModel @Inject constructor() : ViewModel() {
    private val _healthSetupUiState = MutableStateFlow(HealthSetupUiState())
    val healthSetupUiState = _healthSetupUiState.asStateFlow()

    fun onWeightChange(weight: String) {
        _healthSetupUiState.update {
            it.copy(
                weight = weight,
            )
        }
        updateBmi()
    }

    fun onHeightChange(height: String) {
        _healthSetupUiState.update {
            it.copy(height = height)
        }
        updateBmi()
    }

    private fun calculateBmi(weight: Double, height: Double): Double {
        return weight / (height / 100.0).pow(2)
    }

    private fun updateBmi() {
        val weight = _healthSetupUiState.value.weight.toDoubleOrNull()
        val height = _healthSetupUiState.value.height.toDoubleOrNull()

        val bmi = if (weight != null && height != null && height > 0) {
            calculateBmi(weight, height)
        } else {
            0.0
        }

        _healthSetupUiState.update {
            it.copy(yourBmi = bmi.toFormattedBmi())
        }
    }
}
data class HealthSetupUiState(
    val currentStep: HealthSetupStep = HealthSetupStep.BASIC_INFORMATION,
    val weight: String = "",
    val height: String = "",
    val yourBmi: String = "0.0",
    val chest: String = "",
    val waist: String = "",
    val hip: String = "",
)