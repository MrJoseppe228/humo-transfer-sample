package com.example.humo_transfer_sample.TransferActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.databinding.ActivityTransferBinding

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryName = intent.getStringExtra("COUNTRY_NAME")

        binding.apply {
            headTransferText.text = "Перевод в $countryName"

            backButton.setOnClickListener { finish() }

            doTransferButton.setOnClickListener() {

                if ((cardNumber.text.toString().length == 16) && (transferAmount.text.toString() != "0") && (transferAmount.text.toString() != "")) {
                    Toast.makeText(this@TransferActivity, "Оплата прошла успешно)", Toast.LENGTH_LONG).show()
                    finish()
                }
                else {
                    Toast.makeText(this@TransferActivity, "Неправильные данные", Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}