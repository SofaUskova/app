package com.example.myapplication.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.models.Location
import com.example.myapplication.models.Seller
import com.example.myapplication.models.UserPrincipal
import com.example.myapplication.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences.Editor
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_registration)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        initListeners()
        initObservable()
    }

    private fun initObservable() {
        viewModel.isSuccess.observe(this, Observer {
            if (it) {
                createSharedPreference()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Произошла ошибка на сервере", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createSharedPreference() {
        sharedPreferences =
            getSharedPreferences("SellerPreference", MODE_PRIVATE).edit()
        sharedPreferences.apply {
            putString("NAME", inputEditTextName.text.toString())
            putString("PHONE", inputEditTextPhone.text.toString())
            putString("LOCATION", inputEditTextLocation.text.toString())
        }.apply()
    }

    private fun initListeners() {
        entry.setOnClickListener {
            onBackPressed()
        }

        buttonNextBase.setOnClickListener {
            val name = inputEditTextName.text.toString()
            val phone = inputEditTextPhone.text.toString()
            val location = inputEditTextLocation.text.toString()
            val login = inputEditTextLogin.text.toString()
            val password = inputEditTextPassword.text.toString()

            if (phone.length == 10) {
                val newSeller = Seller(
                    idSeller = 0,
                    name = name,
                    phone = phone,
                    //TODO
                    location = Location(idLocation = 1, city = location),
                    userPrincipal = UserPrincipal(login, password)
                )

                if (name.isNotEmpty() && phone.isNotEmpty() && location.isNotEmpty() && login.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.addSeller(newSeller)
                } else {
                    Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Введите номер телефона без пробелов и без кода страны", Toast.LENGTH_SHORT).show()
            }
        }
    }
}