package com.example.lealgym.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        clickBtn()
    }

    fun validateData() {
        val email = binding.emailLogin.text.toString().trim()
        val password = binding.passworLogin.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            binding.progressBar.isVisible = true
            login(email, password)
        } else {
            Toast.makeText(requireContext(),
                "Email ou Senha não informados", Toast.LENGTH_SHORT).show()
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)
                } else {
                    binding.progressBar.isVisible = false
                    println(task.exception)
                }
            }
    }

    private fun clickBtn() {
        binding.buttonRegister.setOnClickListener {
            validateData()
        }
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