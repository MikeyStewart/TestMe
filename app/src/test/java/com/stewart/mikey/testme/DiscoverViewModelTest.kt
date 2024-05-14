package com.stewart.mikey.testme

import app.cash.turbine.test
import com.stewart.mikey.testme.core.domain.GetLatestListingsUseCase
import com.stewart.mikey.testme.core.domain.Listing
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Error
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Loading
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Success
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Uninitialised
import com.stewart.mikey.testme.feature.discover.DiscoverViewModel
import com.stewart.mikey.testme.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for DiscoverViewModel that verify the view state.
 */
class DiscoverViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: DiscoverViewModel
    private val getLatestListingsUseCase: GetLatestListingsUseCase = mockk()

    @Test
    fun `when listings call succeeds, expect Success state`() = runTest {
        // Arrange
        val mockResult = emptyList<Listing>()
        coEvery { getLatestListingsUseCase() } returns mockResult
        viewModel = DiscoverViewModel(getLatestListingsUseCase)

        viewModel.uiState.test {
            // Act
            viewModel.loadListings()

            // Assert
            assertEquals(Uninitialised, awaitItem())
            assertEquals(Loading, awaitItem())
            assertEquals(Success(mockResult), awaitItem())
        }
    }

    @Test
    fun `when listings call fails, expect Error state`() = runTest {
        // Arrange
        val mockException = Exception("Oops!")
        coEvery { getLatestListingsUseCase() } throws mockException
        viewModel = DiscoverViewModel(getLatestListingsUseCase)

        viewModel.uiState.test {
            // Act
            viewModel.loadListings()

            // Assert
            assertEquals(Uninitialised, awaitItem())
            assertEquals(Loading, awaitItem())
            assertEquals(Error(mockException.message), awaitItem())
        }
    }
}