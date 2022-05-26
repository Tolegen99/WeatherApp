package kz.tolegen.weatherapp.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.tolegen.weatherapp.App
import kz.tolegen.weatherapp.common.Constants
import kz.tolegen.weatherapp.data.remote.Api
import kz.tolegen.weatherapp.data.remote.repository.WeatherRepositoryImpl
import kz.tolegen.weatherapp.domain.repository.WeatherRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApi(): Api {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient
            .addInterceptor(Interceptor.invoke {
                val original = it.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(Constants.PARAM_APP_ID, Constants.API_KEY)
                    .addQueryParameter(Constants.PARAM_LANG, Constants.VALUE_LANG)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                it.proceed(request)
            })
            .addInterceptor(logging)
            .addInterceptor(ChuckerInterceptor(App.INSTANCE.applicationContext))


        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(Api::class.java)
    }


    @Provides
    @Singleton
    fun provideWeatherInfoRepository(api: Api): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}