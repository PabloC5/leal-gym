package com.example.lealgym.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentFormTreinoBinding
import com.example.lealgym.databinding.FragmentListaExercicioBinding

class ListaExercicioFragment : Fragment() {
    private var _binding: FragmentListaExercicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaExercicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickAdd()
    }

    fun clickAdd() {
        binding.btnAddEx.setOnClickListener {
            findNavController().navigate(R.id.action_listaExercicioFragment_to_exercicioFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}