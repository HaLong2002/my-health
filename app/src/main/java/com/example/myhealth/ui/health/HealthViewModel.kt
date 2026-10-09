package com.example.myhealth.ui.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.core.data.repository.BodyMeasurementRepository
import com.example.myhealth.core.model.BodyMeasurementSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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
}