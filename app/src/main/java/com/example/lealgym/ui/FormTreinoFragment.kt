package com.example.lealgym.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentFormTreinoBinding
import java.util.Calendar
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.EditText

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}