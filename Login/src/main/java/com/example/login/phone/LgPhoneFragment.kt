package com.example.login.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.example.login.LgClickEvent
import com.example.login.LgLoginViewModel
import com.example.login.R
import com.example.login.databinding.FragmentLgPhoneBinding

class LgPhoneFragment : Fragment() {
    private lateinit var binding: FragmentLgPhoneBinding
    private val loginViewModel: LgLoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLgPhoneBinding.inflate(inflater, container, false)
        binding.viewModel = loginViewModel
        binding.events = LgClickEvent()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.verifyEditText.addTextChangedListener {
            loginViewModel.phoneNumber.postValue(it.toString())
        }
    }
}