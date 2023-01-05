package com.azlanjamal.velp

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.azlanjamal.common.R
import com.azlanjamal.detail_presentation.DetailScreen
import com.azlanjamal.home_presentation.HomeScreen
import com.azlanjamal.search_presentation.SearchScreen
import com.azlanjamal.velp.composable.PermissionDialog
import com.azlanjamal.velp.location.LocationService
import com.azlanjamal.velp.navigation.Route
import com.azlanjamal.velp.ui.theme.VelpTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VelpTheme {
                val permissionsState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                    )
                )
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val lifecycleOwner = LocalLifecycleOwner.current

                DisposableEffect(
                    key1 = lifecycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            when (event) {
                                Lifecycle.Event.ON_START -> {
                                    permissionsState.launchMultiplePermissionRequest()
                                }
                                Lifecycle.Event.ON_RESUME -> {
                                    Intent(applicationContext, LocationService::class.java).apply {
                                        action = LocationService.ACTION_START
                                        startService(this)
                                    }
                                }
                                Lifecycle.Event.ON_STOP, Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_DESTROY -> {
                                    Intent(applicationContext, LocationService::class.java).apply {
                                        action = LocationService.ACTION_STOP
                                        startService(this)
                                    }
                                }
                                else -> {}
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    Log.d("Padding values", "$it")
                    NavHost(
                        navController = navController,
                        startDestination = Route.HOME
                    ) {
                        composable(Route.HOME) {
                            HomeScreen(
                                onNavigateToSearch = {
                                    navController.navigate(Route.SEARCH)
                                }
                            )
                        }
                        composable(Route.SEARCH) {
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                onNavigateToDetail = { businessId ->
                                    navController.navigate(
                                        Route.DETAIL + "/$businessId"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Route.DETAIL + "/{businessId}",
                            arguments = listOf(
                                navArgument("businessId") {
                                    type = NavType.StringType
                                }
                            )
                        ) { entry ->
                            val businessId = entry.arguments?.getString("businessId")!!
                            DetailScreen(businessId = businessId)
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    permissionsState.permissions.forEach { perm ->
                        when(perm.permission) {
                            Manifest.permission.ACCESS_COARSE_LOCATION -> {
                                if (!perm.status.isGranted) {
                                    val textToShow = if (perm.status.shouldShowRationale) {
                                        getString(R.string.location_permission_rationale)
                                    } else {
                                        getString(R.string.location_permission_denied)
                                    }
                                    PermissionDialog(
                                        openDialog = mutableStateOf(true),
                                        message = textToShow
                                    )
                                }
                            }
                            Manifest.permission.ACCESS_FINE_LOCATION -> {
                                if (!perm.status.isGranted) {
                                    val textToShow = if (perm.status.shouldShowRationale) {
                                        getString(R.string.location_permission_rationale)
                                    } else {
                                        getString(R.string.location_permission_denied)
                                    }
                                    PermissionDialog(
                                        openDialog = mutableStateOf(true),
                                        message = textToShow
                                    )
                                }
                            }

                        }
                    }

                }
            }
        }
    }
}