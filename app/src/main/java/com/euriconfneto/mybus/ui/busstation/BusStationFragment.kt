package com.euriconfneto.mybus.ui.busstation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.euriconfneto.mybus.R
import com.euriconfneto.mybus.databinding.FragmentBusstationBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class BusStationFragment : Fragment(), OnMapReadyCallback, View.OnClickListener {

    private lateinit var busStationViewModel: BusStationViewModel
    private var _binding: FragmentBusstationBinding? = null
    private lateinit var mMap: GoogleMap
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        busStationViewModel =
            ViewModelProvider(this).get(BusStationViewModel::class.java)

        _binding = FragmentBusstationBinding.inflate(inflater, container, false)

        binding.imageSearch.setOnClickListener(this)

        busStationViewModel.authentication()

        //Criar Mapa
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fragment_map_bus_station) as SupportMapFragment
        mapFragment.getMapAsync(this)

        observer()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )

    }

    override fun onClick(v: View) {
        if(v.id == R.id.image_search){
            val termSearch = binding.editSearch.text.toString()
            if (termSearch != ""){
                busStationViewModel.getBusStation(termSearch)
            }else{
                Toast.makeText(context, R.string.error_empty_text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observer(){
        busStationViewModel.authentication.observe(viewLifecycleOwner){
            if (!it){
                Toast.makeText(context, R.string.error_authentication, Toast.LENGTH_SHORT).show()
            }
        }

        busStationViewModel.listLocation.observe(viewLifecycleOwner){ list ->
            val latLng = LatLng(list[0].stationLatitude, list[0].stationLongitude)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F))
            list.forEach{
                mMap.addMarker(MarkerOptions()
                    .position(LatLng(it.stationLatitude, it.stationLongitude))
                    .title(it.stationName))
            }
        }
    }
}
