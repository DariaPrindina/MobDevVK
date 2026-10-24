package com.example.mobdevvk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mobdevvk.data.apps
import com.example.mobdevvk.data.showcaseApps
import com.example.mobdevvk.ui.appdetails.AppDetailsScreen
import com.example.mobdevvk.ui.applist.AppListScreen
import kotlinx.serialization.Serializable

@Serializable
object AppListRoute

@Serializable
data class AppDetailsRoute(val appId: String)

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppListRoute,
        modifier = modifier,
    ) {
        composable<AppListRoute> {
            AppListScreen(
                apps = apps,
                onAppClick = { app -> navController.navigate(AppDetailsRoute(app.id)) },
            )
        }
        composable<AppDetailsRoute> { backStackEntry ->
            val appId = backStackEntry.toRoute<AppDetailsRoute>().appId
            val app = apps.first { it.id == appId }
            AppDetailsScreen(
                app = app,
                similarApps = apps.filter { it.id != appId },
                showcaseApps = showcaseApps,
                onBackClick = { navController.popBackStack() },
                onShowcaseAppClick = {
                    navController.popBackStack(AppListRoute, inclusive = false)
                },
            )
        }
    }
}
