package com.example.login.verify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lib_base.bmob.UserManager
import com.example.login.LgLoginViewModel
import com.example.login.R
import com.example.login.databinding.FragmentLgVerifyBinding
import com.example.login.utils.ViewUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class lgVerifyFragment : Fragment() {
    private lateinit var binding: FragmentLgVerifyBinding
    private val viewModel: LgLoginViewModel by activityViewModels()
    private var time = 59

    // val viewModel = LgLoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLgVerifyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.time <= 0) requestSms()
        binding.phoneTextView.text = viewModel.phoneNumber.value

        binding.timerTextView.setOnClickListener {
            if (binding.timerTextView.text != "重新发送") return@setOnClickListener
            viewModel.time = time
            requestSms()
        }

        binding.verifyEditText.addTextChangedListener { editableText ->
            if (editableText?.length == 6) {
                viewModel.verifyAndLogin(editableText.toString()) {
                    if (it) {
                        requireActivity().finish()
                    } else {
                        ViewUtils.showAlert(requireContext(), "验证码错误", false)
                    }
                }
            }
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_lgVerifyFragment_to_lgPhoneFragment)
        }

        //密码登录
        binding.passwordLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_lgVerifyFragment_to_lgPasswordFragment)
        }
    }

    fun requestSms() {
        viewModel.requestSms(
            onStart = {
                lifecycleScope.launch {
                    while (true) {
                        delay(1000)
                        viewModel.time--
                        if (viewModel.time == -1) {
                            binding.timerTextView.text = "重新发送"
                            break
                        }
                        binding.timerTextView.text = "$viewModel.time"
                    }
                }
            },
            onEnd = {
                if (it) {
                    ViewUtils.showAlert(requireContext(), "短信发送成功", false)
                } else {
                    ViewUtils.showAlert(requireContext(), "请求验证码失败", false)
                }
            })
    }
}