package com.example.kbctrafficlightsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kbctrafficlightsample.ui.screen.carscreen.CarModelScreen
import com.example.kbctrafficlightsample.ui.screen.carscreen.CarModelScreenViewModel
import com.example.kbctrafficlightsample.ui.screen.trafficlisght.TrafficLightScreen
import com.example.kbctrafficlightsample.ui.screen.trafficlisght.TrafficLightScreenViewModel
import com.example.kbctrafficlightsample.ui.theme.KbcTrafficLightSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KbcTrafficLightSampleTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.Starting.route) {
                    composable(Routes.Starting.route) {
                        CarModelScreen(
                            onCarModelConfirmed = { model ->
                                navController.navigate("${Routes.TrafficLight.route}/$model")
                            },
                            viewModel = viewModel<CarModelScreenViewModel>(
                                factory = CarModelScreenViewModel.Factory
                            )
                        )
                    }

                    composable(
                        "${Routes.TrafficLight.route}/{model}",
                        arguments = listOf(
                            navArgument("model") { type = NavType.StringType }
                        )
                    ) {
                        val carModel = it.arguments?.getString("model") ?: "N/A"
                        TrafficLightScreen(
                            carModel,
                            viewModel = viewModel<TrafficLightScreenViewModel>(
                                factory = TrafficLightScreenViewModel.Factory
                            )
                        )
                    }
                }
            }
        }
    }
}
