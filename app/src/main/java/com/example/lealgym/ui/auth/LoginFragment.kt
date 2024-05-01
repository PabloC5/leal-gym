package com.example.lealgym.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickBtn()
    }

    private fun clickBtn() {
//        controla a navegação dos botões da tela de login
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment2)
        }

        binding.buttonRecover.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_recoverFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}