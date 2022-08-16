package com.euriconfneto.mybus.ui.lane

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.euriconfneto.mybus.R
import com.euriconfneto.mybus.databinding.FragmentLaneBinding
import com.euriconfneto.mybus.ui.adapter.LineAdapter

class LaneFragment : Fragment(), View.OnClickListener  {

    private var _binding: FragmentLaneBinding? = null
    private val binding get() = _binding!!
    private val adapter = LineAdapter()
    private lateinit var activityContext: Context
    private lateinit var laneViewModel: LaneViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        laneViewModel =
            ViewModelProvider(this).get(LaneViewModel::class.java)

        _binding = FragmentLaneBinding.inflate(inflater, container, false)

        //Preenchendo o spiner
        val spinnerAdapter = ArrayAdapter(
            activityContext,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("Todos", "Term. Secundario", "Term. Principal"))
        binding.spinnerDirection.adapter = spinnerAdapter

        //RecyclerView
        binding.recyclerLine.layoutManager = LinearLayoutManager(context)
        binding.recyclerLine.adapter = adapter

        //Eventos
        binding.imageSearch.setOnClickListener(this)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View) {
        if(view.id == R.id.image_search){
            val term = binding.editSearchLine.text.toString()
            val index = binding.spinnerDirection.selectedItemId.toByte()
            laneViewModel.getBusLine(term, index)

        }
    }

    private fun observer(){
        laneViewModel.busLines.observe(viewLifecycleOwner){
            adapter.updateLis(it)
        }
    }
}