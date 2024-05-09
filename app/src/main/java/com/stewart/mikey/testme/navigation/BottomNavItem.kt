package com.stewart.mikey.testme.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.stewart.mikey.testme.R
import com.stewart.mikey.testme.feature.discover.DiscoverRoute
import com.stewart.mikey.testme.feature.mytrademe.MyTradeMeRoute
import com.stewart.mikey.testme.feature.watchlist.WatchlistRoute

sealed class BottomNavItem(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
    val route: NavigationRoute,
) {
    object DiscoverNavItem :
        BottomNavItem(R.string.bottom_nav_discover, R.drawable.search, DiscoverRoute)

    object WatchlistNavItem :
        BottomNavItem(R.string.bottom_nav_watchlist, R.drawable.ic_binoculars, WatchlistRoute)

    object MyTradeMeNavItem :
        BottomNavItem(R.string.bottom_nav_my_trade_me, R.drawable.profile, MyTradeMeRoute)
}
