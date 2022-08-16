package com.euriconfneto.mybus.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.euriconfneto.mybus.databinding.RowLineBinding
import com.euriconfneto.mybus.repository.model.BusLineModel
import com.euriconfneto.mybus.ui.viewholder.LineViewHolder

class LineAdapter : RecyclerView.Adapter<LineViewHolder>() {

    private var listLine: List<BusLineModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RowLineBinding.inflate(inflater, parent, false)
        return LineViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        holder.binData(listLine[position])
    }

    override fun getItemCount(): Int {
        return listLine.count()
    }

    fun updateLis(list: List<BusLineModel>){
        listLine = list
    }
}