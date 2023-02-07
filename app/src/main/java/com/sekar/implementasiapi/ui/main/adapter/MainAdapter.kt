package com.sekar.implementasiapi.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sekar.implementasiapi.R
import com.sekar.implementasiapi.data.model.Forecastday
import com.sekar.implementasiapi.data.model.WeatherData
import com.sekar.implementasiapi.databinding.ItemWeatherDayBinding

class MainAdapter(private var forecast: List<Forecastday>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ItemWeatherDayBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: Forecastday) {
            val humidity = "${weather.day.avghumidity}%"
            val hour = weather.day.avgtempC.toString()

            binding.apply {
                tvHumidity.text = humidity
                tvHour.text = hour
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        ItemWeatherDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = forecast.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(forecast[position])
    }

    fun addForecast(forecast1: List<Forecastday>) {
        forecast = forecast1
    }
}