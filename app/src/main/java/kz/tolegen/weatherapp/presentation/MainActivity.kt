package kz.tolegen.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kz.tolegen.weatherapp.presentation.citydetail.CityDetailScreen
import kz.tolegen.weatherapp.presentation.citylist.CityListScreen
import kz.tolegen.weatherapp.presentation.ui.theme.WeatherAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CityListScreen.route
                    ) {
                        composable(
                            route = Screen.CityListScreen.route
                        ) {
                            CityListScreen(navController)
                        }
                        composable(
                            route = Screen.CityDetailScreen.route + "/{cityName}"
                        ) {
                            CityDetailScreen()
                        }
                    }
                }
            }
        }
    }
}