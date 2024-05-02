package com.example.lealgym.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lealgym.databinding.ItemAdapterBinding
import com.example.lealgym.model.Treino
import java.text.SimpleDateFormat

class TreinoAdapter(
    private var context: Context,
    private val treinoList: List<Treino>,
    val treinoSelect: (Treino, Int) -> Unit
) : RecyclerView.Adapter<TreinoAdapter.MyViewHolder>() {

    companion object {
        val SELECT_REMOVE: Int = 1
        val SELECT_EDIT: Int = 2
        val SELECT_EX: Int = 3

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val treino = treinoList[position]

        holder.binding.textNumber.text = treino.nome
        holder.binding.textDesc.text = treino.descricao
        val formato = SimpleDateFormat("dd/MM/yyyy")
        val dateFormat = formato.format(treino.date!!)

        holder.binding.textDate.text = dateFormat

        holder.binding.bttDelete.setOnClickListener { treinoSelect(treino, SELECT_REMOVE) }
        holder.binding.bttEdit.setOnClickListener { treinoSelect(treino, SELECT_EDIT) }
        holder.binding.bttExercicio.setOnClickListener { treinoSelect(treino, SELECT_EX) }

    }

    override fun getItemCount() = treinoList.size

    inner class MyViewHolder(val binding: ItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

}