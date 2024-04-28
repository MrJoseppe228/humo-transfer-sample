package com.example.humo_transfer_sample.MainActivity.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.humo_transfer_sample.ChooseCountryActivity.Adapter.CountryAdapter
import com.example.humo_transfer_sample.CountryData
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.databinding.ItemCountryBinding
import com.example.humo_transfer_sample.databinding.ItemCountrySquareBinding

class PopularCountryAdapter(val listener: Listener): RecyclerView.Adapter<PopularCountryAdapter.PopularCountryHolder>() {

    private val countryList = ArrayList<CountryData>()

    fun getCountryList(): ArrayList<CountryData> {
        return countryList
    }

    class PopularCountryHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemCountrySquareBinding.bind(item)
        fun bind(country: CountryData, listener: Listener) = with(binding) {
            rcCountryImage.setImageResource(country.image)
            rcCountryName.text = country.name

            itemView.setOnClickListener {
                listener.onClick(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCountryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_square, parent, false)
        return PopularCountryHolder(view)
    }

    override fun onBindViewHolder(holder: PopularCountryHolder, position: Int) {
        holder.bind(countryList[position], listener)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addCountry(country: CountryData) {
        countryList.add(country)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(country: CountryData)
    }
}