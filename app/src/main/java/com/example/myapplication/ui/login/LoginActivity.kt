package com.example.myapplication.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.models.Seller
import com.example.myapplication.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferencesGet: SharedPreferences
    private lateinit var sharedPreferences: SharedPreferences.Editor
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        isLogin()
        initObservable()
        initListener()
    }

    private fun isLogin() {
        sharedPreferencesGet = getSharedPreferences(
            "SellerPreference",
            MODE_PRIVATE
        )
        if (sharedPreferencesGet.getString("LOGIN", "") != null && sharedPreferencesGet.getString("LOGIN", "") != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun initListener() {
        registration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        buttonNextBase.setOnClickListener {
            val login = inputEditTextLogin.text.toString()
            val password = inputEditTextPassword.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                viewModel.getSeller(login)
            } else {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initObservable() {
        viewModel.currentSeller.observe(this, Observer {
            if (it != null) {
                createSharedPreference(it)

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Не верный логин", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createSharedPreference(seller: Seller) {
        sharedPreferences = getSharedPreferences("SellerPreference", MODE_PRIVATE).edit()
        sharedPreferences.apply {
            putString("IDSELLER", seller.idSeller.toString())
            putString("LOGIN", seller.userPrincipal.login)
            putString("NAME", seller.name)
            putString("PHONE", seller.phone)
            putString("LOCATION", seller.location.city)
        }.apply()
    }
}