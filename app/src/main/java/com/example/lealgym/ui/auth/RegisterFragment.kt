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
import com.example.lealgym.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        clickRegister()
    }

    fun clickRegister() {
        binding.buttonRegister.setOnClickListener {
            validateData()
        }
    }

    fun validateData() {
        val email = binding.idEmailRegister.text.toString().trim()
        val password = binding.idPasswordRegister.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            binding.progressBarRegister.isVisible = true
            register(email, password)
        } else {
            Toast.makeText(requireContext(),
                "Email ou Senha não informados", Toast.LENGTH_SHORT).show()
        }
    }

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_global_homeFragment)
                } else {
                    binding.progressBarRegister.isVisible = false
                    println(task.exception)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}