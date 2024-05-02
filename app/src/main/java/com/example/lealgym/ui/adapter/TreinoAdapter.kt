package com.example.lealgym.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lealgym.databinding.ItemAdapterBinding
import com.example.lealgym.model.Treino

class TreinoAdapter(
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
        holder.binding.textDate.text = treino.date.toString()

        holder.binding.bttDelete.setOnClickListener { treinoSelect(treino, SELECT_REMOVE) }
        holder.binding.bttEdit.setOnClickListener { treinoSelect(treino, SELECT_EDIT) }
        holder.binding.bttExercicio.setOnClickListener { treinoSelect(treino, SELECT_EX) }

    }

    override fun getItemCount() = treinoList.size

    inner class MyViewHolder(val binding: ItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

}