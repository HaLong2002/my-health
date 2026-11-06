package com.example.myhealth.ui.health.setup

import androidx.lifecycle.ViewModel
import com.example.myhealth.core.model.HealthSetupStep
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HealthSetupViewModel @Inject constructor() : ViewModel() {
    private val _healthSetupUiState = MutableStateFlow(HealthSetupUiState())
    val healthSetupUiState = _healthSetupUiState.asStateFlow()
}

data class HealthSetupUiState(
    val currentStep: HealthSetupStep = HealthSetupStep.BASIC_INFORMATION,
    val weight: String = "",
    val height: String = "",
    val chest: String = "",
    val waist: String = "",
    val hip: String = "",
)