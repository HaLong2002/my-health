package com.example.myhealth.ui.health.setup

import androidx.lifecycle.ViewModel
import com.example.myhealth.core.model.HealthSetupStep
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HealthSetupViewModel @Inject constructor() : ViewModel() {
    private val _healthSetupUiState = MutableStateFlow(HealthSetupUiState())
    val healthSetupUiState = _healthSetupUiState.asStateFlow()

    fun onWeightChange(weight: String) {
        _healthSetupUiState.update {
            it.copy(weight = weight)
        }
    }

    fun onHeightChange(height: String) {
        _healthSetupUiState.update {
            it.copy(height = height)
        }
    }
}

data class HealthSetupUiState(
    val currentStep: HealthSetupStep = HealthSetupStep.BASIC_INFORMATION,
    val weight: String = "",
    val height: String = "",
    val chest: String = "",
    val waist: String = "",
    val hip: String = "",
)