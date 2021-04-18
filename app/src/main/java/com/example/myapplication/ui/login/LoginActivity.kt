package com.example.myapplication.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.models.Seller
import com.example.myapplication.ui.MainActivity
import kotlinx.android.synthetic.main.login_fragment.*

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences.Editor
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_fragment)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        initObservable()
        initListener()
    }

    private fun initListener() {
        registration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        buttonNextBase.setOnClickListener {
            val login = inputEditTextLogin.text.toString()
            val password = inputEditTextPassword.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                viewModel.entry(login)
            } else {
                //TODO если не зарегистрирован
            }
        }
    }

    private fun initObservable() {
        viewModel.currentSeller.observe(this, Observer<Seller> {
            sharedPreferences = getSharedPreferences("SellerPreference", MODE_PRIVATE).edit()
            sharedPreferences.apply {
                putString("NAME", it.name)
                putString("PHONE", it.phone)
                putString("LOCATION", it.location.city)
            }.apply()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }
}