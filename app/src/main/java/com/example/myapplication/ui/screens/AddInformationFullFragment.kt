package com.example.myapplication.ui.screens

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.models.*
import com.example.myapplication.ui.viewModels.AddInformationFullViewModel
import com.example.myapplication.utils.*
import kotlinx.android.synthetic.main.fragment_add_full_information.*
import kotlinx.android.synthetic.main.fragment_profile.*

class AddInformationFullFragment : Fragment() {
    private val specializationDialogFragment = DialogFragment()
    private var colorPosition = 1
    private var breedPosition = 1
    private lateinit var addInformationFullFragment: AddInformationFullViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addInformationFullFragment =
            ViewModelProvider(this).get(AddInformationFullViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_full_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbarInfoBase = view.findViewById<Toolbar>(R.id.toolbar)

        initToolbar(toolbarInfoBase)
        initListeners(view)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            3 -> {
                inputEditTextColor.setText(data?.getStringExtra("value"))
                colorPosition = data?.getIntExtra("position", 1)!!
                colorPosition++
            }
            4 -> {
                inputEditTextBreed.setText(data?.getStringExtra("value"))
                breedPosition = data?.getIntExtra("position", 1)!!
                breedPosition++
            }
        }
    }

    private fun initToolbar(toolbarInfoBase: Toolbar) {
        toolbarInfoBase.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun initListeners(view: View) {
        deleteError(inputEditTextHeight, inputTextHeight)
        deleteError(inputEditTextSpecifity, inputTextSpecifity)
        deleteError(inputEditTextPrice, inputTextPrice)

        selectItem(inputEditTextColor, "color", 3, this, requireContext())
        selectItem(inputEditTextBreed, "breed", 4, this, requireContext())
        selectItemDialogFragment(
            inputEditTextSpecifity,
            "specialization",
            specializationDialogFragment,
            childFragmentManager
        )

        extendedFabClean.setOnClickListener {
            NavHostFragment.findNavController(view.findFragment()).apply {
                popBackStack(R.id.navigation_add_information, true)
            }
        }

        extendedFabPush.setOnClickListener {
            setError()

            if (isNotEmptyEditText()) {

                sharedPreferences = requireActivity().getSharedPreferences(
                    "SellerPreference",
                    AppCompatActivity.MODE_PRIVATE
                )

                val seller = Seller(
                    sharedPreferences.getString("IDSELLER", "0")?.toInt() ?: 0,
                    sharedPreferences.getString("NAME", "") ?: "",
                    sharedPreferences.getString("PHONE", "") ?: "",
                    Location(1, city = sharedPreferences.getString("LOCATION", "")?: "Москва"),
                    UserPrincipal(sharedPreferences.getString("LOGIN", "") ?: "", "g")
                )

                val color = Color(colorPosition, inputEditTextColor.text.toString())
                val breed = Breed(breedPosition, inputEditTextBreed.text.toString())
                val gender =
                    getGenderType(arguments?.getString("gender", GenderType.FEMALE.value)!!)
                val location = Location(
                    idLocation = 1,
                    city = ((arguments?.get("extras") as Bundle).getString("loc")!!)
                )
                val docType = getDocType(
                    (arguments?.get("extras") as Bundle).getString(
                        "docType",
                        DocType.PASSPORT.value
                    )
                )
                val allInfo = inputEditTextInfo.text.toString()
                val price = inputEditTextPrice.text.toString()

                val newHorse = Horse(
                    idHorse = 1,
                    name = (arguments?.get("extras") as Bundle).getString("name")!!,
                    height = inputEditTextHeight.text.toString(),
                    yearBirth = arguments?.getString("birth")!!,
                    organization = (arguments?.get("extras") as Bundle).getString("org")!!,
                    brand = arguments?.getString("brand")!!,
                    marks = arguments?.getString("marks")!!,
                    breed = breed,
                    color = color,
                    gender = gender,
                    location = location,
                    mother = arguments?.getString("mother"),
                    father = arguments?.getString("father"),
                    allInform = allInfo
                )

                addInformationFullFragment.addNewHorse(view, newHorse, price, seller)
            }
        }
    }

    private fun setError() {
        if (inputEditTextColor.text.isNullOrEmpty()) inputTextColor.error = "Заполните поле"
        if (inputEditTextBreed.text.isNullOrEmpty()) inputTextBreed.error = "Заполните поле"
        if (inputEditTextHeight.text.isNullOrEmpty()) inputTextHeight.error = "Заполните поле"
        if (inputEditTextSpecifity.text.isNullOrEmpty()) inputTextSpecifity.error =
            "Заполните поле"
        if (inputEditTextPrice.text.isNullOrEmpty()) inputTextPrice.error = "Заполните поле"
    }

    private fun isNotEmptyEditText() = !inputEditTextColor.text.isNullOrEmpty()
            && !inputEditTextBreed.text.isNullOrEmpty()
            && !inputEditTextHeight.text.isNullOrEmpty()
            && !inputEditTextSpecifity.text.isNullOrEmpty()
            && !inputEditTextPrice.text.isNullOrEmpty()

    fun onClicked(str: String) {
        specializationDialogFragment.dismiss()
        inputEditTextSpecifity.setText(str)
    }
}