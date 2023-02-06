package com.sekar.implementasiapi.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sekar.implementasiapi.data.api.ApiHelper
import com.sekar.implementasiapi.data.api.RetrofitBuilder
import com.sekar.implementasiapi.data.model.WeatherDay
import com.sekar.implementasiapi.databinding.ActivityMainBinding
import com.sekar.implementasiapi.utils.Status.ERROR
import com.sekar.implementasiapi.utils.Status.LOADING
import com.sekar.implementasiapi.utils.Status.SUCCESS
import com.sekar.implementasiapi.ui.base.ViewModelFactory
import com.sekar.implementasiapi.ui.main.adapter.MainAdapter
import com.sekar.implementasiapi.ui.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupViewModel()
            setupUI()
            setupObservers()
        }

        private fun setupViewModel() {
            viewModel = ViewModelProvider(this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.getRetrofit()))
            ).get(MainViewModel::class.java)
        }

        private fun setupUI() {
            binding.apply {
                binding.rvWeatherDay.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = MainAdapter(arrayListOf())
                }
            }
        }

        private fun setupObservers() {
            viewModel.getForecast().observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        SUCCESS -> {
                            binding.rvWeatherDay.visibility = View.VISIBLE
                            binding.pbLoading.visibility = View.GONE
                            resource.data?.let { forecast -> retrieveList(forecast) }
                        }
                        ERROR -> {
                            binding.rvWeatherDay.visibility = View.VISIBLE
                            binding.pbLoading.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        LOADING -> {
                            binding.pbLoading.visibility = View.VISIBLE
                            binding.rvWeatherDay.visibility = View.GONE
                        }
                    }
                }
            })
        }

        private fun retrieveList(forecast: WeatherDay) {
            adapter.apply {
                addForecast(forecast)
            }
        }
    }