package com.example.lealgym.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.lealgym.databinding.FragmentExercicioBinding
import com.example.lealgym.helper.FirebaseHelper
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.annotation.Nullable


class ExercicioFragment : Fragment() {
    private var _binding: FragmentExercicioBinding? = null
    private val binding get() = _binding!!
    private lateinit var editTextDate: EditText
    var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExercicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        click()
    }

    fun registerTreino(nome: String,
                       observacao: String,imagem: String
    ) {

        val data = hashMapOf(
            "nome" to nome,
            "observacao" to observacao,
            "imagem" to imageUri,
        )
        upImage()
        FirebaseHelper.getDatabase().collection("Exercicio").add(data)
            .addOnSuccessListener { Log.d("dbinfolog", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("dbinfolog", "Error writing document", e) }
    }

    fun click() {
        binding.selectImage.setOnClickListener{
            selectImage()
        }

        binding.buttonTreino.setOnClickListener {
            validateData()
        }
    }

    fun validateData() {
        val nome = binding.editTextName.text.toString().trim()
        val observacao = binding.editTextObs.text.toString().trim()
        val imagem = "teste"

        if (nome.isNotEmpty() && observacao.isNotEmpty() && imagem.isNotEmpty()) {
            binding.progressBar.isVisible = true
            registerTreino(nome, observacao, imagem)
        } else {
            Toast.makeText(requireContext(),
                "Email ou Senha não informados", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null && data.data != null) {
            imageUri = data.data
//            binding.firebaseimage.setImageURI(imageUri)
        }
    }

    fun upImage() {
        val storageRef = FirebaseHelper.getStorageFire().reference
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA)
        val now = Date()
        val fileName = formatter.format(now)
        val refStorage = storageRef.child("imagensGym/${fileName}")

        refStorage.putFile(imageUri!!)
            .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot?> {
            }).addOnFailureListener(OnFailureListener {
            })
    }

    private fun selectImage() {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, 100)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}