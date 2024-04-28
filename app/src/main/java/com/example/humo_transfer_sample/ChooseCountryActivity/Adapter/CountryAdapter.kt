package com.example.humo_transfer_sample.ChooseCountryActivity.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.humo_transfer_sample.CountryData
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.databinding.ItemCountryBinding


class CountryAdapter(val listener: Listener): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var countryList = ArrayList<CountryData>()

    class CountryViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ItemCountryBinding.bind(item)

        fun bind(country: CountryData, listener: Listener) = with(binding) {
            countryImage.setImageResource(country.image)
            countryName.text = country.name
            itemView.setOnClickListener {
                listener.onClick(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryAdapter.CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position], listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: ArrayList<CountryData>) {
        countryList = filterList
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(country: CountryData)
    }
}