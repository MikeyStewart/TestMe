package com.stewart.mikey.testme.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stewart.mikey.testme.ui.theme.BluffOyster600

/**
 * Simple custom navigation bar that matches designs.
 */
@Composable
fun CustomNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        shadowElevation = NavigationBarElevation,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .height(NavigationBarHeight)
                .selectableGroup(),
            content = content,
        )
    }
}

@Composable
fun RowScope.CustomNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .weight(1f)
            .clickable { onClick() }
            .padding(NavigationBarItemPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColour(selected)) {
            icon()
        }
        Spacer(modifier = Modifier.height(NavigationBarItemPadding))
        CompositionLocalProvider(LocalTextStyle provides labelTextStyle(selected)) {
            label?.invoke()
        }
    }
}

@Composable
private fun labelTextStyle(selected: Boolean) =
    MaterialTheme.typography.labelMedium.copy(color = contentColour(selected))

@Composable
private fun contentColour(selected: Boolean) =
    if (selected) MaterialTheme.colorScheme.primary else BluffOyster600

private val NavigationBarHeight = 80.dp
private val NavigationBarElevation = 8.dp
private val NavigationBarItemPadding: Dp = 8.dp
