package kz.tolegen.weatherapp.presentation

sealed class Screen(val route: String) {
    object CityListScreen: Screen("city_list_screen")
    object CityDetailScreen: Screen("city_detail_screen")
}