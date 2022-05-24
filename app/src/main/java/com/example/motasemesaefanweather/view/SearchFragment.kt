package com.example.motasemesaefanweather.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.motasemesaefanweather.R
import com.example.motasemesaefanweather.databinding.FragmentSearchBinding
import com.example.motasemesaefanweather.repository.WeatherForecastRepositoryImp
import com.example.motasemesaefanweather.viewmodel.WeatherForecastViewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding ? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    lateinit var weatherRecycleAdapter: WeatherRecycleAdapter

    private val viewModel: WeatherForecastViewModel by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WeatherForecastViewModel(WeatherForecastRepositoryImp()) as T
            }
        }.create(WeatherForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        Log.d("ViewModel", "$viewModel")
        binding.btnSearch.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WeatherFragment.newInstance(binding.citySearch.text.toString()))
                .addToBackStack(null)
                .commit()
        }


        return binding.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

