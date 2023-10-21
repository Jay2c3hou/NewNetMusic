package com.example.login.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.login.LgLoginViewModel

import com.example.login.R
import com.example.login.databinding.FragmentLgPasswordBinding
import com.example.login.utils.ViewUtils

class lgPasswordFragment : Fragment() {
    private lateinit var binding: FragmentLgPasswordBinding
    private val viewModel: LgLoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLgPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener {
            ViewUtils.dismissLoading()
            findNavController().navigateUp()
        }

        binding.passwordEditText.addTextChangedListener {
            if (it == null) {
                binding.loginBtn.isEnabled = false
            } else {
                binding.loginBtn.isEnabled = it.isNotEmpty()
            }
        }
        //登录按钮
        binding.loginBtn.setOnClickListener {
            val password = binding.passwordEditText.text.toString()
            viewModel.loginByPassword(password,
                onStart = {
                    ViewUtils.showIsLoading(requireContext())
                },
                onEnd = {
                    if (it) {
                        requireActivity().finish()
                    } else {
                        ViewUtils.dismissLoading()
                        ViewUtils.showAlert(requireContext(), "密码错误", false)
                        binding.passwordEditText.setText("")
                    }
                })
        }
    }

}