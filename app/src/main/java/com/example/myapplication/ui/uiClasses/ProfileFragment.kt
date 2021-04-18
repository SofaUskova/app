package com.example.myapplication.ui.uiClasses

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ui.viewModels.ProfileViewModel
import com.example.myapplication.utils.convertPixelsToDp
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        sharedPreferences = requireActivity().getSharedPreferences("SellerPreference", AppCompatActivity.MODE_PRIVATE)
        sellerName.text = sharedPreferences.getString("NAME", "")
        sellerPhone.text = sharedPreferences.getString("PHONE", "")
        sellerLocation.text = sharedPreferences.getString("LOCATION", "")

       // initListeners(view)

        val image = ImageView(requireContext())
        image.layoutParams = LinearLayout.LayoutParams(
            convertPixelsToDp(325f, requireContext()).toInt(),
            convertPixelsToDp(325f, requireContext()).toInt()
        )
        image.scaleType = ImageView.ScaleType.CENTER_CROP
        image.setImageResource(R.drawable.d)
        //linearLayoutAdverbs.addView(image)
    }

//    private fun initListeners(view: View) {
//        buttonYourAdverbs.setOnClickListener {
//            NavHostFragment.findNavController(view.findFragment()).navigate(
//                R.id.action_navigation_profile_to_adverbsListFragment
//            )
//        }
//    }
}