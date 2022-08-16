package com.euriconfneto.mybus.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.euriconfneto.mybus.R
import com.euriconfneto.mybus.databinding.RowLineBinding
import com.euriconfneto.mybus.repository.model.BusLineModel


class LineViewHolder (
    private val itemBinding: RowLineBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

    fun binData(busLine: BusLineModel){
        itemBinding.textAsnwerMain.text = busLine.firstSign
        itemBinding.textAsnwerSecond.text = busLine.secondSign.toString()
        if (busLine.circularLine){
            itemBinding.textAsnwerCircular.text = R.string.answer_true.toString()
        } else {
            itemBinding.textAsnwerCircular.text = R.string.answer_false.toString()
        }
        if (busLine.directionLine == 10){
            val text = "Base (${busLine.directionLine})"
            itemBinding.textAsnwerDirection.text = text
        } else {
            val text = "Atendimento (${busLine.directionLine})"
            itemBinding.textAsnwerDirection.text = text
        }
    }
}