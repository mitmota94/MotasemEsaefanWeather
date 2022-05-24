package com.example.motasemesaefanweather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WeatherForecastResponse(
    val list: List<WeatherForecast>
)

@Parcelize
data class WeatherForecast(


    val dt : Long,
    val main: WeatherTemp,
    val weather : List<WeatherDesc>,
    val clouds : WeatherCloud,
    //val wind : WeatherWind,
    //val sys : WeatherSys,
    val dt_txt : String
): Parcelable


@Parcelize
data class WeatherTemp(
    var temp: Double,
    var feels_like : Double
): Parcelable
@Parcelize
data class WeatherDesc(
    val main : String,
    val description : String,
): Parcelable
@Parcelize
data class WeatherCloud(
    val all : Int
): Parcelable

/*
@Parcelize
data class WeatherWind(
    val speed : Double,
    val deg : Int,
    val gust : Int
): Parcelable
@Parcelize
data class WeatherSys(
    val pod : String
): Parcelable
 */