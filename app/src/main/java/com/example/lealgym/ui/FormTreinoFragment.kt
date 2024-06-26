package com.example.lealgym.ui

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentFormTreinoBinding
import com.example.lealgym.helper.FirebaseHelper
import com.example.lealgym.model.Exercicio
import com.example.lealgym.model.Treino
import java.text.DateFormat
import java.util.Calendar
import java.util.Date
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

class FormTreinoFragment : Fragment() {
    private var _binding: FragmentFormTreinoBinding? = null
    private val binding get() = _binding!!
    private lateinit var editTextDate: EditText
    private lateinit var treino: Treino
    private var novoTreino: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormTreinoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextDate = view.findViewById(R.id.editTextDate)
        editTextDate.setOnClickListener { openDatePicker() }
        click()
    }
    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                editTextDate.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    fun registerTreino(data: Treino) {
        FirebaseHelper.getDatabase().collection("Treino").add(data)
            .addOnSuccessListener {
                Log.d("dbinfolog", "DocumentSnapshot successfully written!")
                binding.progressBar.isVisible = false
                binding.editText.text = null
                binding.editText2.text = null
                binding.editTextDate.text = null
                findNavController().popBackStack()
                Toast.makeText(requireContext(),
                    "Treino salvo com sucesso!!",
                    Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e -> Log.w("dbinfolog", "Error writing document", e) }
    }

    fun click() {
        binding.buttonTreino.setOnClickListener {
            validateData()
        }
    }

    fun validateData() {
        val nome = binding.editText.text.toString().trim()
        val descricao = binding.editText2.text.toString().trim()
        val date = binding.editTextDate.text.toString().trim()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateFinal = dateFormat.parse(date)
        if (nome.isNotEmpty() && descricao.isNotEmpty() && date.isNotEmpty()) {
            binding.progressBar.isVisible = true
            if (novoTreino) treino = Treino()
            treino.nome = nome
            treino.descricao = descricao
            treino.date = dateFinal
            treino.id_user = FirebaseHelper.getIdUser()!!

            registerTreino(treino)
        } else {
            Toast.makeText(requireContext(),
                "Não foi possivel salvar", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}