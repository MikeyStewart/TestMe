package com.stewart.mikey.testme.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.stewart.mikey.testme.core.data.ListingsRepository
import com.stewart.mikey.testme.core.domain.GetLatestListingsUseCase
import com.stewart.mikey.testme.core.data.ApiService
import com.stewart.mikey.testme.core.data.client
import com.stewart.mikey.testme.feature.discover.DiscoverRoute
import com.stewart.mikey.testme.feature.discover.DiscoverScreen
import com.stewart.mikey.testme.feature.discover.DiscoverViewModel
import com.stewart.mikey.testme.feature.mytrademe.MyTradeMeRoute
import com.stewart.mikey.testme.feature.mytrademe.MyTradeMeScreen
import com.stewart.mikey.testme.feature.watchlist.WatchlistRoute
import com.stewart.mikey.testme.feature.watchlist.WatchlistScreen
import com.stewart.mikey.testme.navigation.BottomNavItem.DiscoverNavItem
import com.stewart.mikey.testme.navigation.BottomNavItem.MyTradeMeNavItem
import com.stewart.mikey.testme.navigation.BottomNavItem.WatchlistNavItem

@Composable
fun RootNavigation() {
    val bottomNavItems = listOf(
        DiscoverNavItem,
        WatchlistNavItem,
        MyTradeMeNavItem,
    )
    val navController = rememberNavController()
    val discoverViewModel = DiscoverViewModel(
        GetLatestListingsUseCase(
            ListingsRepository(
                ApiService(client)
            )
        )
    ) // TODO: Implement DI

    Scaffold(
        bottomBar = {
            CustomNavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { navItem ->
                    val selected = currentDestination?.hasRoute(navItem.route::class) == true
                    CustomNavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(navItem.icon),
                                contentDescription = null,
                            )
                        },
                        label = { Text(text = stringResource(navItem.name)) },
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DiscoverRoute,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(navigationTransitionDuration)) },
            exitTransition = { fadeOut(animationSpec = tween(navigationTransitionDuration)) },
        ) {
            composable<DiscoverRoute> {
                DiscoverScreen(discoverViewModel)
            }
            composable<WatchlistRoute> {
                WatchlistScreen()
            }
            composable<MyTradeMeRoute> {
                MyTradeMeScreen()
            }
        }
    }
}

private const val navigationTransitionDuration = 300
