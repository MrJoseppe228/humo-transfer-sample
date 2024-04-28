package com.example.humo_transfer_sample.MainActivity

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humo_transfer_sample.ChooseCountryActivity.Adapter.CountryAdapter
import com.example.humo_transfer_sample.ChooseCountryActivity.ChooseCountryActivity
import com.example.humo_transfer_sample.CountryData
import com.example.humo_transfer_sample.MainActivity.Adapter.PopularCountryAdapter
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.TransferActivity.TransferActivity
import com.example.humo_transfer_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PopularCountryAdapter.Listener {

    private lateinit var binding: ActivityMainBinding
    private var sharedPreferences: SharedPreferences? = null
    private val adapter = PopularCountryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LOGIN_TABLE", Context.MODE_PRIVATE)

        val countryList: ArrayList<CountryData> = arrayListOf(
            CountryData("Таджикистан", R.drawable.ic_tj),
            CountryData("Россия", R.drawable.ic_ru),
            CountryData("Узбекистан", R.drawable.ic_uz)
        )
        init(countryList)

        binding.transferButton.setOnClickListener {
            val intent = Intent(this, ChooseCountryActivity::class.java)
            startActivity(intent)
        }

        binding.updateButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=tj.humo.transfer")))
        }

        binding.exitUserButton.setOnClickListener {
            sharedPreferences?.edit()?.putBoolean("signedIn", false)?.apply()
            finish()
        }


    }

    private fun init(countryList: ArrayList<CountryData>) = with(binding) {
        popularCountriesRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false )
        popularCountriesRecyclerview.adapter = adapter
        countryList.forEach { adapter.addCountry(it) }
    }

    override fun onClick(country: CountryData) {
        val intent = Intent(this, TransferActivity::class.java)
        intent.putExtra("COUNTRY_NAME", country.name)
        startActivity(intent)
    }
}