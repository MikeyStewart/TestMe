package com.stewart.mikey.testme.feature.discover

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.stewart.mikey.testme.R
import com.stewart.mikey.testme.core.domain.Listing
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Error
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Loading
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Success
import com.stewart.mikey.testme.feature.discover.DiscoverUiState.Uninitialised
import com.stewart.mikey.testme.ui.theme.topBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(viewModel: DiscoverViewModel) {
    val context = LocalContext.current
    val actions = listOf(
        R.string.action_search to R.drawable.search,
        R.string.action_cart to R.drawable.cart,
    )
    val uiState by viewModel.uiState.collectAsState()

    // TODO: Update how/where listings are loaded so this isn't called when recomposed
    LaunchedEffect(Unit) {
        viewModel.loadListings()
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.title_browse),
                            style = MaterialTheme.typography.topBarTitle,
                        )
                    },
                    actions = {
                        actions.forEach { action ->
                            val actionName = stringResource(action.first)
                            IconButton(onClick = {
                                Toast.makeText(
                                    context,
                                    "$actionName not implemented",
                                    Toast.LENGTH_LONG,
                                ).show()
                            }) {
                                Icon(
                                    painter = painterResource(action.second),
                                    contentDescription = actionName,
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                    },
                )
                Divider()
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (uiState) {
                is Error -> {
                    FullScreenError(
                        (uiState as Error).errorMessage
                            ?: stringResource(id = (R.string.error_message_generic))
                    )
                }

                Loading -> {
                    FullScreenLoading()
                }

                is Success -> {
                    DiscoverScreenList((uiState as Success).listings)
                }

                Uninitialised -> {
                    // Do nothing
                }
            }
        }
    }
}

@Composable
fun DiscoverScreenList(listings: List<Listing>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        items(listings, key = { it.listingId }) { listing ->
            Column {
                ListingCard(listing)
                Divider()
            }
        }
    }
}

@Composable
fun FullScreenError(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message)
    }
}

@Composable
fun FullScreenLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // TODO: (Future improvement) replace with *fancy shimmer*
        CircularProgressIndicator()
    }
}
