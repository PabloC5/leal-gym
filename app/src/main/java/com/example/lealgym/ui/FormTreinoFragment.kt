package com.example.lealgym.ui

import android.app.DatePickerDialog
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
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentFormTreinoBinding
import com.example.lealgym.helper.FirebaseHelper
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
                // Do something with the selected date
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                editTextDate.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    fun registerTreino(nome: String,
                       descricao: String, date: Date) {

        val data = hashMapOf(
            "nome" to nome,
            "idUser" to FirebaseHelper.getIdUser(),
            "descricao" to descricao,
            "date" to date,
        )

        FirebaseHelper.getDatabase().collection("LealGym").add(data)
            .addOnSuccessListener { Log.d("dbinfolog", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("dbinfolog", "Error writing document", e) }
    }

    fun click() {
        binding.buttonTreino.setOnClickListener {
            validateData()
        }
    }

    fun validateData() {
        val nome = binding.editText.text.toString().trim()
        val idUser = binding.editText.text.toString().trim()
        val descricao = binding.editText2.text.toString().trim()
        val date = binding.editTextDate.text.toString().trim()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        var teste = dateFormat.parse(date)
        if (nome.isNotEmpty() && descricao.isNotEmpty() && date.isNotEmpty()) {
            binding.progressBar.isVisible = true
            registerTreino(nome, descricao, teste!!)
        } else {
            Toast.makeText(requireContext(),
                "Email ou Senha não informados", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}