package com.example.myhealth.ui.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.core.common.result.Result
import com.example.myhealth.core.common.result.asResult
import com.example.myhealth.core.data.repository.BodyMeasurementRepository
import com.example.myhealth.core.model.BodyMeasurementSummary
import com.example.myhealth.core.model.HealthDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HealthViewModel @Inject constructor(
    bodyMeasurementRepository: BodyMeasurementRepository,
) : ViewModel() {
    val bodyMeasurementSummary: StateFlow<BodyMeasurementSummary> =
        bodyMeasurementRepository.getBodyMeasurementSummary()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BodyMeasurementSummary(),
            )

    val healthUiState: StateFlow<HealthUiState> =
        healthUiState()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HealthUiState.Loading,
            )

    private fun healthUiState(): Flow<HealthUiState> =
        bodyMeasurementSummary
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success ->
                        HealthUiState.Success(
                            healthDataStatus = HealthDataStatus(
                                hasBodyMeasurements = result.data.hasMeasurements
                            )
                        )

                    is Result.Error -> HealthUiState.Error
                    is Result.Loading -> HealthUiState.Loading
                }
            }
}

sealed interface HealthUiState {
    data class Success(val healthDataStatus: HealthDataStatus) : HealthUiState
    data object Loading : HealthUiState
    data object Error : HealthUiState
}