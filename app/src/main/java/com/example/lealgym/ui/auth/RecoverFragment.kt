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
import com.example.lealgym.databinding.FragmentRecoverBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RecoverFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentRecoverBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        clickBtn()
    }

    private fun clickBtn() {
        binding.btRecover.setOnClickListener {
            validateData()
        }
    }


    fun validateData() {
        val email = binding.emailRecover.text.toString().trim()

        if (email.isNotEmpty()) {
            binding.progressBar.isVisible = true
            recover(email)
        } else {
            Toast.makeText(requireContext(),
                "Email ou Senha não informados", Toast.LENGTH_SHORT).show()
        }
    }

    fun recover(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(),
                        "Link de recuperação de senha enviado no email",
                        Toast.LENGTH_SHORT).show()
                }
                binding.progressBar.isVisible = false
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}