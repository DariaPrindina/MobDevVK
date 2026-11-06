package com.example.mobdevvk.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobdevvk.presentation.appdetails.AppDetailsScreen
import com.example.mobdevvk.presentation.applist.AppListScreen
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
                onAppClick = { app -> navController.navigate(AppDetailsRoute(app.id)) },
                viewModel = hiltViewModel(),
            )
        }
        composable<AppDetailsRoute> {
            AppDetailsScreen(
                viewModel = hiltViewModel(),
                onBackClick = { navController.popBackStack() },
                onShowcaseAppClick = {
                    navController.popBackStack(AppListRoute, inclusive = false)
                },
            )
        }
    }
}
