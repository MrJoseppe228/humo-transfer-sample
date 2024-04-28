package com.example.humo_transfer_sample.LoginActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.humo_transfer_sample.MainActivity.MainActivity
import com.example.humo_transfer_sample.R
import com.example.humo_transfer_sample.databinding.ActivityLoginBinding
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var sharedPreferences: SharedPreferences? = null

    @SuppressLint("ResourceAsColor", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("LOGIN_TABLE", Context.MODE_PRIVATE)
        val intent = Intent(this, MainActivity::class.java)
        val signedIn: Boolean?
        val isNotFirstLogin = sharedPreferences?.getBoolean("first", false)
        if (isNotFirstLogin == true) {
            signedIn = sharedPreferences?.getBoolean("signedIn",false)
            if (signedIn == true) {
                startActivity(intent)
            }
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            privacyCheckBox.setOnClickListener {
                var checkedColor = ContextCompat.getColor(this@LoginActivity, R.color.button_color)
                if (privacyCheckBox.isChecked){

                    loginButton.setBackgroundColor(checkedColor)
                    checkedColor = ContextCompat.getColor(this@LoginActivity, R.color.button_active_color)
                    loginButton.setTextColor(checkedColor)
                    loginButton.isEnabled = true
                }
                else {
                    checkedColor = ContextCompat.getColor(this@LoginActivity, R.color.login_button_tint_color)
                    loginButton.setBackgroundColor(checkedColor)
                    checkedColor = ContextCompat.getColor(this@LoginActivity, R.color.button_notactive_color)
                    loginButton.setTextColor(checkedColor)
                    loginButton.isEnabled = false
                }
            }


            loginButton.setOnClickListener {
                if (phoneNumber.text.toString().length == 13) {
                    sharedPreferences?.edit()?.putBoolean("first", true)?.apply()
                    sharedPreferences?.edit()?.putBoolean("signedIn", true)?.apply()
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this@LoginActivity, "Формат ввода номера +992 XX XXX XXXX", Toast.LENGTH_SHORT).show()
                }
            }

            exitButton.setOnClickListener {
                Toast.makeText(this@LoginActivity, "Нажмите ещё раз", Toast.LENGTH_SHORT).show()
                onDestroy()
            }

        }

    }
}