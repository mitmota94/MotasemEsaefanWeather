package com.example.motasemesaefanweather.repository

import android.util.Log
import com.example.motasemesaefanweather.api.ApiService
import com.example.motasemesaefanweather.model.WeatherForecastResponse

interface WeatherForecastRepository{
    suspend fun geWeatherForecast(q:String): WeatherForecastResponse

}
class WeatherForecastRepositoryImp(private val  service: ApiService = ApiService.getApiService()):WeatherForecastRepository{

    override suspend fun geWeatherForecast(cityName: String): WeatherForecastResponse {
        val response = service.getWeatherForecast(q = cityName)
        return if (response.isSuccessful){
            response.body()!!
        }else{
            Log.d("asd","Network is shit2")
            WeatherForecastResponse(emptyList())
        }
    }
}


