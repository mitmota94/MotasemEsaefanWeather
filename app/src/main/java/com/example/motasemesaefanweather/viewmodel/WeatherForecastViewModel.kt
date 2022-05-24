package com.example.motasemesaefanweather.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.motasemesaefanweather.model.WeatherForecastResponse
import com.example.motasemesaefanweather.repository.WeatherForecastRepositoryImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch





class WeatherForecastViewModel (val repositoryImp: WeatherForecastRepositoryImp) : ViewModel(){

    private val _weatherForecast = MutableLiveData<WeatherForecastResponse>()
    val weatherLive: LiveData<WeatherForecastResponse> get() = _weatherForecast


    fun getWeatherForecast(cityName:String){
        Log.d("asd",cityName)
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryImp.geWeatherForecast(cityName =cityName)
            _weatherForecast.postValue(response)
        }
    }





}