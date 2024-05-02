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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SplashFragment2 : Fragment() {
    private var _binding: FragmentSplash2Binding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

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
        auth = Firebase.auth
        if (auth.currentUser == null) {
            findNavController().navigate(R.id.action_splashFragment2_to_navigation2)
        } else {
            findNavController().navigate(R.id.action_splashFragment2_to_homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}