package com.example.motasemesaefanweather.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motasemesaefanweather.databinding.ActivityMainBinding
import com.example.motasemesaefanweather.databinding.WeatherListItemBinding
import com.example.motasemesaefanweather.model.WeatherForecast

class WeatherRecycleAdapter (
        private val weatherList: MutableList<WeatherForecast> = mutableListOf(),
        private val openDetails:(WeatherForecast) -> Unit
 ):RecyclerView.Adapter<WeatherRecycleAdapter.WeatherViewHolder>(){

     fun setWeatherList(newList: List<WeatherForecast>){
         weatherList.clear()
         weatherList.addAll(newList)
         notifyDataSetChanged()
     }
    inner class WeatherViewHolder(
        private val binding: WeatherListItemBinding
    ):RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherForecast: WeatherForecast) {
            binding.tvDesc.text = weatherForecast.weather[0].main.toString()
            binding.tvTemp.text = weatherForecast.main.temp.toInt().toString() + "  "+ "K"
            binding.root.setOnClickListener {
                openDetails(weatherForecast)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder(
            WeatherListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )


    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount() = weatherList.size

}

