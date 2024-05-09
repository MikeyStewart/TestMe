package com.stewart.mikey.testme.feature.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stewart.mikey.testme.domain.GetLatestListingsUseCase
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Error
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Loading
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Success
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Uninitialised
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DiscoverViewModel(private val getLatestListings: GetLatestListingsUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<DiscoverUiState> = MutableStateFlow(Uninitialised)
    val uiState: StateFlow<DiscoverUiState> = _uiState.asStateFlow()

    fun loadListings() {
        _uiState.update { Loading }

        viewModelScope.launch {
            try {
                val result = getLatestListings()
                _uiState.update { Success(result) }
            } catch (e: Exception) {
                _uiState.update { Error(e.message) }
            }
        }
    }
}
