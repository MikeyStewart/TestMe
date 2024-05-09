package com.stewart.mikey.testme.feature.watchlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.stewart.mikey.testme.R
import com.stewart.mikey.testme.ui.theme.topBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen() {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.title_watchlist),
                            style = MaterialTheme.typography.topBarTitle,
                        )
                    },
                )
                Divider()
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Watchlist not implemented")
        }
    }
}
