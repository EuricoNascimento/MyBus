package com.euriconfneto.mybus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.euriconfneto.mybus.R
import com.euriconfneto.mybus.databinding.FragmentHomeBinding
import com.euriconfneto.mybus.repository.model.BusStationModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var mMap: GoogleMap
    private var list = listOf<BusStationModel>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.authentication()

        //Criar Mapa
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fragment_map_home) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Procura procura parada
        homeViewModel.getBusStation("Afonso")

        observer()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if(list.isEmpty()) {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(0.0, 0.0))
                    .title("Marker")
            )
        } else {
            val latLng = LatLng(list[0].stationLatitude, list[0].stationLongitude)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            list.forEach{
                mMap.addMarker(MarkerOptions()
                    .position(LatLng(it.stationLatitude, it.stationLongitude))
                    .title(it.stationName))
            }
        }
    }

    private fun observer(){
        homeViewModel.authentication.observe(viewLifecycleOwner){
            if (!it){
                Toast.makeText(context, R.string.error_authentication, Toast.LENGTH_SHORT).show()
            }
        }

        homeViewModel.listLocation.observe(viewLifecycleOwner){
            list = it
            /*val latLng = LatLng(list[0].stationLatitude, list[0].stationLongitude)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            list.forEach{
                mMap.addMarker(MarkerOptions()
                    .position(LatLng(it.stationLatitude, it.stationLongitude))
                    .title(it.stationName))
            }*/
        }
    }
}
