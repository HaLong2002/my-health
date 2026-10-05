package com.example.myhealth.ui.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.core.data.repository.HealthRepository
import com.example.myhealth.core.model.BodyMeasurement
import com.example.myhealth.core.model.HealthProfile
import com.example.myhealth.core.model.WeightRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HealthViewModel @Inject constructor(
    private val healthRepository: HealthRepository,
) : ViewModel() {
    val healthUiState: StateFlow<HealthUiState> = healthUiState(
        healthRepository = healthRepository,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HealthUiState(),
    )

    private fun healthUiState(
        healthRepository: HealthRepository,
    ): Flow<HealthUiState> =
        combine(
            healthRepository.observeHealthProfile(),
            healthRepository.observeLatestWeight(),
            healthRepository.observeLatestBodyMeasurement(),
        ) { profile, weight, bodyMeasurement ->
            HealthUiState(
                weightRecord = weight,
                healthProfile = profile,
                bodyMeasurement = bodyMeasurement,
            )

        }
}

data class HealthUiState(
    val weightRecord: WeightRecord? = null,
    val healthProfile: HealthProfile? = null,
    val bodyMeasurement: BodyMeasurement? = null,
)
