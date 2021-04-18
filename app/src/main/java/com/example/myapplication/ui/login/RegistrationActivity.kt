package com.example.myapplication.ui.login

import android.content.Intent
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
import kotlinx.android.synthetic.main.registration_fragment.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_fragment)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        initListeners()
        initObservable()
    }

    private fun initObservable() {
        viewModel.isSuccessful.observe(this, Observer {
            if(it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initListeners() {
        buttonNextBase.setOnClickListener {
            val name = inputEditTextName.text.toString()
            val phone = inputEditTextPhone.text.toString()
            val location = inputEditTextLocation.text.toString()
            val login = inputEditTextLogin.text.toString()
            val password = inputEditTextPassword.text.toString()

            val newSeller = Seller(
                idSeller = 5,
                name = name,
                phone = phone,
                location = Location(1, "Россия", location),
                login = UserPrincipal(login, password),
                image = null,
                favoriteHorses = null
            )

            if (name.isNotEmpty() && phone.isNotEmpty() && location.isNotEmpty() && login.isNotEmpty() && password.isNotEmpty()) {
                viewModel.addSeller(newSeller)
            } else {
                //TODO вывод ошибки
            }
        }
    }

}