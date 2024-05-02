package com.example.lealgym.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lealgym.databinding.ItemAdapterBinding
import com.example.lealgym.model.Treino

class TreinoAdapter(
    private val treinoList: List<Treino>
) : RecyclerView.Adapter<TreinoAdapter.MyViewHolder>() {

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

        holder.binding.textNumber.text = treino.nome.toString()
        holder.binding.textDesc.text = treino.descricao
        holder.binding.textDate.text = treino.date.toString()
    }

    override fun getItemCount() = treinoList.size

    inner class MyViewHolder(val binding: ItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

}