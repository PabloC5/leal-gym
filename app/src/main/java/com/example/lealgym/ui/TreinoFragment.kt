package com.example.lealgym.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lealgym.R
import com.example.lealgym.databinding.FragmentTreinoBinding
import com.example.lealgym.helper.FirebaseHelper
import com.example.lealgym.model.Treino
import com.example.lealgym.ui.adapter.TreinoAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TreinoFragment : Fragment() {
    private var _binding: FragmentTreinoBinding? = null
    private val binding get() = _binding!!
    private var listaTreino = mutableListOf<Treino>()

    private lateinit var treinoAdapter: TreinoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTreinoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickAdd()
        getTreino()
    }

    fun clickAdd() {
        binding.btnAddTreino.setOnClickListener {
//            findNavController().navigate(R.id.action_listaExercicioFragment_to_exercicioFragment)
            findNavController().navigate(R.id.action_homeFragment_to_formTreinoFragment)
        }
    }


    private fun getTreino() {
            FirebaseHelper.getDatabase()
            .collection("Treino")
            .whereEqualTo("id_user", FirebaseHelper.getIdUser())
            .addSnapshotListener {  snapshot, e ->
                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    Toast.makeText(requireContext(),
                        "Erro ao recuperar os treinos", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    listaTreino.clear()
                    Log.d(ContentValues.TAG, "Current data: ${snapshot.metadata}")
                    snapshot.forEach { document ->
                        val nome = document.get("nome")
                        val descricao = document.get("descricao")
                        val id_user = document.get("id_user")
                        val exercicio = document.get("exercicio")
                        val date = convertDate(document.get("date"))
                        val treino = Treino(
                            nome.toString(),
                            id_user.toString(),
                            descricao.toString(),
                            date
                        )
                        binding.textInfo.text = ""
                        listaTreino.add(treino)
                    }
                    iniciaAdapter()
                } else {
                    binding.textInfo.text = "Nenhum treino encontrado"
                    Log.d(ContentValues.TAG, "Current data: null")
                }
                binding.progressBar.isVisible = false
            }

    }

    fun convertDate(dateString: Any?): Date? {
        val regex = Regex("Timestamp\\(seconds=(\\d+)")
        val matchResult = regex.find(dateString.toString())
        val seconds = matchResult?.groups?.get(1)?.value?.toLongOrNull()
        seconds?.let {
            return Date(it * 1000)
        }
        return null
    }
    private fun iniciaAdapter() {
        binding.recvTreino.layoutManager = LinearLayoutManager(requireContext())
        binding.recvTreino.setHasFixedSize(true)
        treinoAdapter = TreinoAdapter(requireContext(), listaTreino) { treino, select ->
            optionSelect(treino, select)
        }
        binding.recvTreino.adapter = treinoAdapter
    }

    private fun optionSelect(treino: Treino, selected: Int) {
        when(selected) {
            TreinoAdapter.SELECT_REMOVE -> {
                removeTreino(treino)
            }
        }
    }

    private fun removeTreino(treino: Treino) {
        Toast.makeText(requireContext(),
            "Para implementações futuras", Toast.LENGTH_SHORT).show()
//        FirebaseHelper.getDatabase().collection("Treino").document(treino.id_user)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}