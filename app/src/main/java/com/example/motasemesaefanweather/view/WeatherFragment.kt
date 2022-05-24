package com.example.motasemesaefanweather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.motasemesaefanweather.R
import com.example.motasemesaefanweather.databinding.FragmentWeatherBinding
import com.example.motasemesaefanweather.model.WeatherForecast
import com.example.motasemesaefanweather.repository.WeatherForecastRepositoryImp
import com.example.motasemesaefanweather.viewmodel.WeatherForecastViewModel

class WeatherFragment : Fragment() {

    private var _binding : FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding get() = _binding!!
    //private val openDetails : (WeatherForecast) -> Unit

    lateinit var weatherRecycleAdapter: WeatherRecycleAdapter

    private val viewModel : WeatherForecastViewModel by lazy {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WeatherForecastViewModel(WeatherForecastRepositoryImp()) as T
            }
        }.create(WeatherForecastViewModel::class.java)
    }

    companion object{

        const val KEY = "WeatherFragmentKey"
        fun newInstance(cityName: String): WeatherFragment{
            val fragment = WeatherFragment()
            val bundle = Bundle()
            bundle.putString(KEY,cityName)
            fragment.arguments=bundle
            return fragment

        }


    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(layoutInflater)
        val cityName = arguments?.getString(KEY)
        if (cityName != null) {
            viewModel.getWeatherForecast(cityName)
            configureObserver()
        }
        return binding.root

    }

    private fun configureObserver(){

        weatherRecycleAdapter = WeatherRecycleAdapter(openDetails = ::openDetails)
        viewModel.weatherLive.observe(viewLifecycleOwner) { response ->
            if(response.list.isEmpty()){
                binding.tvErrorText.text = "Network is shit"
            } else {
                weatherRecycleAdapter.setWeatherList(response.list)
                binding.apply {
                    rvWeather.adapter = weatherRecycleAdapter
                    pbLoading.visibility = View.GONE
                    tvErrorText.visibility = View.GONE
                }
            }
        }
    }

    private fun openDetails(weatherFor: WeatherForecast){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment.newInstance(weatherFor))
            .addToBackStack(null)
            .commit()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





//

}