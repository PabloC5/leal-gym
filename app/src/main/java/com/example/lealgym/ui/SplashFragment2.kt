package com.example.lealgym.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentSplash2Binding

class SplashFragment2 : Fragment() {
    private var _binding: FragmentSplash2Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplash2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(this::checkLogin, 3000)
    }

    private fun checkLogin() {
//        manda o usuario para a tela de login após checar se esta logado
        findNavController().navigate(R.id.action_splashFragment2_to_loginFragment2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}