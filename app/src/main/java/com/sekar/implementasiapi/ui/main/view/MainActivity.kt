package com.sekar.implementasiapi.ui.main.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sekar.implementasiapi.data.api.ApiHelper
import com.sekar.implementasiapi.data.api.RetrofitBuilder
import com.sekar.implementasiapi.data.model.Forecastday
import com.sekar.implementasiapi.databinding.ActivityMainBinding
import com.sekar.implementasiapi.ui.base.ViewModelFactory
import com.sekar.implementasiapi.ui.main.adapter.MainAdapter
import com.sekar.implementasiapi.ui.main.viewmodel.MainViewModel
import com.sekar.implementasiapi.utils.Status.*

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
                    layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
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
                            resource.data?.let { forecast -> retrieveList(forecast.forecast.forecastday) }
                            Toast.makeText(this, "MUNCUL", Toast.LENGTH_LONG).show()
                            Log.e(TAG, "setupObservers: BERHASIL COY")
                        }
                        ERROR -> {
                            binding.rvWeatherDay.visibility = View.VISIBLE
                            binding.pbLoading.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            Log.e(TAG, "setupObservers: " + it.message)
                        }
                        LOADING -> {
                            binding.pbLoading.visibility = View.VISIBLE
                            binding.rvWeatherDay.visibility = View.GONE

                            Log.e(TAG, "setupObservers: LOADING")
                        }
                    }
                }
            })
        }

        private fun retrieveList(forecast: List<Forecastday>) {
            adapter.apply {
                addForecast(forecast)
            }
        }
    }