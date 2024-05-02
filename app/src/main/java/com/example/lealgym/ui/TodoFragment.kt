package com.example.lealgym.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickAdd()
    }

    fun clickAdd() {
        binding.btnAddTreino.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_exercicioFragment)
//            findNavController().navigate(R.id.action_homeFragment_to_formTreinoFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}