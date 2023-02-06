package com.sekar.implementasiapi.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sekar.implementasiapi.data.model.WeatherDay
import com.sekar.implementasiapi.databinding.ItemWeatherDayBinding

class MainAdapter(private val forecast: ArrayList<WeatherDay>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ItemWeatherDayBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: WeatherDay) {
            val humidity = "${weather.list?.main?.humidity}%"
            val hour = "${weather.list?.dtTxt?.substring(11, 16)}"

            binding.apply {
                tvHumidity.text = humidity
                tvHour.text = hour
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.DataViewHolder {
        val weatherItemBinding =
            ItemWeatherDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(weatherItemBinding)
    }
    override fun getItemCount(): Int = forecast.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(forecast[position])
    }

    fun addForecast(forecast: WeatherDay) {
        this.forecast.apply {
            clear()
            addAll(arrayOf(forecast))
        }
    }
}