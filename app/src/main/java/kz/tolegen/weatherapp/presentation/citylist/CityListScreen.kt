package kz.tolegen.weatherapp.presentation.citylist

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kz.tolegen.weatherapp.domain.model.dateToStringFormat1
import kz.tolegen.weatherapp.domain.model.isoToDate
import kz.tolegen.weatherapp.presentation.Screen
import kz.tolegen.weatherapp.presentation.citylist.composes.CurrentWeatherItem

@Composable
fun CityListScreen(
    navController: NavController,
    viewModel: CityListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val cities by viewModel.readAllData.observeAsState(initial = emptyList())
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isContentReadyToShow) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                        ) {
                            val textState = remember { mutableStateOf(TextFieldValue()) }
                            OutlinedTextField(
                                value = textState.value,
                                onValueChange = { textState.value = it },
                                label = { Text("Название города") }
                            )
                            Button(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    start = 8.dp,
                                    end = 4.dp
                                ),
                                onClick = {
                                    Log.e("TAG", "CityListScreen: $cities")
                                    if (!textState.value.text.isNullOrBlank()) {
                                        val city =
                                            cities.firstOrNull { it.name == textState.value.text }?.name
                                        if (city == null) {
                                            viewModel.findCityAndCurrentWeather(textState.value.text)
                                            viewModel.getWeatherOfCities(cities)
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Данный город уже есть в базе",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Введите название города",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                            ) {
                                Text("Поиск", fontSize = 10.sp)
                            }
                        }
                        Text(
                            text = "Данные были получены ${
                                cities.first().updatedUp.isoToDate()?.dateToStringFormat1()
                            }",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                    bottom = 16.dp
                                    ),
                                onClick = {
                                    viewModel.getWeatherOfCities(cities)
                                },
                            ) {
                                Text("Обновить", fontSize = 10.sp)
                            }
                        }
                    }
                }

                items(items = cities) { city ->
                    CurrentWeatherItem(
                        cityEntity = city,
                        onItemClick = {
                            navController.navigate(Screen.CityDetailScreen.route + "/${city.name}")
                        }
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Toast.makeText(
                context,
                state.error,
                Toast.LENGTH_SHORT
            ).show()
        }
        if (!state.isContentReadyToShow || state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
