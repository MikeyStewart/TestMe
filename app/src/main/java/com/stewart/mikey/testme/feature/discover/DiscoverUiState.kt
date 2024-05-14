package com.stewart.mikey.testme.feature.discover

import com.stewart.mikey.testme.core.domain.Listing

/**
 * A sealed hierarchy representing the state of the Discover screen.
 */
sealed interface DiscoverUiState {

    data object Uninitialised : DiscoverUiState

    data object Loading : DiscoverUiState

    data class Success(val listings: List<Listing>) : DiscoverUiState

    data class Error(val errorMessage: String?) : DiscoverUiState
}