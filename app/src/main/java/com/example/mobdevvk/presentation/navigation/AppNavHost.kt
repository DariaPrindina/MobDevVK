package com.example.mobdevvk.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobdevvk.di.AppContainer
import com.example.mobdevvk.presentation.appdetails.AppDetailsScreen
import com.example.mobdevvk.presentation.appdetails.AppDetailsViewModel
import com.example.mobdevvk.presentation.applist.AppListScreen
import com.example.mobdevvk.presentation.applist.AppListViewModel
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
                viewModel = viewModel { AppListViewModel(AppContainer.appRepository) },
            )
        }
        composable<AppDetailsRoute> {
            AppDetailsScreen(
                viewModel = viewModel {
                    AppDetailsViewModel(createSavedStateHandle(), AppContainer.appRepository)
                },
                onBackClick = { navController.popBackStack() },
                onShowcaseAppClick = {
                    navController.popBackStack(AppListRoute, inclusive = false)
                },
            )
        }
    }
}
