package com.example.motasemesaefanweather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.motasemesaefanweather.R
import com.example.motasemesaefanweather.databinding.FragmentDetailsBinding
import com.example.motasemesaefanweather.model.WeatherForecast

class DetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null
    val binding: FragmentDetailsBinding get() = _binding!!

    companion object {

        const val KEY = "OpenDetailsFragmentKey"
        fun newInstance(weatherFor: WeatherForecast): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY, weatherFor)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        val weatherFor: WeatherForecast? = arguments?.getParcelable(KEY)
        var tempKelvin = weatherFor?.main?.temp!!
        var  tempFeelLike = weatherFor?.main?.feels_like!!
        //  var tempKelvinRnd = tempKelvin.roundToInt()
        binding.apply {
            tvDetailsTemp.text = weatherFor.main.temp.toInt().toString() + " "+ "K"
            tvFeelLikeTemp.text = "Feels Like " + weatherFor.main.feels_like.toInt().toString()+ " " + "K"
            tvCloud.text = weatherFor.weather[0].main.toString()
            tvDesc.text = weatherFor.weather[0].description.toString()
        }
        binding.btnSearch.setOnClickListener {

            var newTemp: String  = when(binding.spTemp.selectedItem.toString())  {
                "Fahrenheit" -> ((tempKelvin- 273.15) * 9/5 + 32).toInt().toString()+  " " + "F"
                "Celsius" -> (tempKelvin - 273.15).toInt().toString() + " " + "C"
                else -> tempKelvin.toInt().toString() + " " + "K"
            }
            //var newTempRnd : Int = newTemp.toInt()
            //binding.tvDetailsTemp.text = newTemp.toString()
            binding.tvDetailsTemp.text = newTemp
            //  binding.tvFeelLikeTemp.text = newTempRnd.toString()
            //  binding.tvFeelLikeTemp.text = newTempRnd.toString()
            var newfeelsLike : String = when(binding.spTemp.selectedItem.toString()){
                "Fahrenheit" -> (((tempFeelLike - 273.15) * (9/5)) + 32).toInt().toString() + " " + "F"
                "Celsius" -> (tempFeelLike - 273.15).toInt().toString() + " " + "C"
                else -> tempFeelLike.toInt().toString() + " " +  "K"
            }
            binding.tvFeelLikeTemp.text = "${"Feels Like "}" + newfeelsLike
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}