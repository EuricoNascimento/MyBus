package com.euriconfneto.mybus.ui.lane

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.euriconfneto.mybus.databinding.FragmentLaneBinding
import com.euriconfneto.mybus.ui.adapter.LineAdapter

class LaneFragment : Fragment() {

    private var _binding: FragmentLaneBinding? = null
    private val binding get() = _binding!!
    private val adapter = LineAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val laneViewModel =
            ViewModelProvider(this).get(LaneViewModel::class.java)

        _binding = FragmentLaneBinding.inflate(inflater, container, false)

        binding.recyclerLine.layoutManager = LinearLayoutManager(context)
        binding.recyclerLine.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}