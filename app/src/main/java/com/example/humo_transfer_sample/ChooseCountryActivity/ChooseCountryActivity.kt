package com.example.humo_transfer_sample.ChooseCountryActivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.humo_transfer_sample.ChooseCountryActivity.Adapter.CountryAdapter
import com.example.humo_transfer_sample.CountryData
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.TransferActivity.TransferActivity
import com.example.humo_transfer_sample.databinding.ActivityChooseCountryBinding

class ChooseCountryActivity : AppCompatActivity(), CountryAdapter.Listener {

    private lateinit var binding: ActivityChooseCountryBinding
    private val adapter = CountryAdapter(this)
    private val countryList: ArrayList<CountryData> = arrayListOf(
        CountryData("Россия", R.drawable.ic_ru),
        CountryData("Узбекистан", R.drawable.ic_uz),
        CountryData("Таджикистан", R.drawable.ic_tj),
        CountryData("Казахстан", R.drawable.ic_kz),
        CountryData("ОАЭ", R.drawable.ic_ae),
        CountryData("Корея", R.drawable.ic_kr)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            countryRecyclerview.layoutManager = LinearLayoutManager(this@ChooseCountryActivity)
            countryRecyclerview.adapter = adapter
            adapter.filterList(countryList)
        }
        binding.searchView.addTextChangedListener(/* watcher = */ object: TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }


        })

        binding.backButton.setOnClickListener { finish() }

    }

    private fun filter(searchText: String) = with(binding){
        var filteredCountryList = countryList
        var newFilteredCountryList = filteredCountryList.filter { it.name.contains(searchText, ignoreCase = true) }//filter returns List<CountryData>
        filteredCountryList = ArrayList<CountryData>()
        for (item in newFilteredCountryList) {
            filteredCountryList.add(item)
        }
        adapter.filterList(filteredCountryList)
    }

    override fun onClick(country: CountryData) {
        val intent = Intent(this, TransferActivity::class.java)
        intent.putExtra("COUNTRY_NAME", country.name)
        startActivity(intent)
    }

}