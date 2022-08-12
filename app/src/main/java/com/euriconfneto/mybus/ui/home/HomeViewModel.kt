package com.euriconfneto.mybus.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.euriconfneto.mybus.BuildConfig
import com.euriconfneto.mybus.listener.APIListener
import com.euriconfneto.mybus.repository.AuthenticationRepository
import com.euriconfneto.mybus.repository.BusStationRepository
import com.euriconfneto.mybus.repository.model.BusStationModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val authenticationRepository = AuthenticationRepository()
    private val busStationRepository = BusStationRepository()
    private val _authentication = MutableLiveData<Boolean>()
    val authentication: LiveData<Boolean> = _authentication
    private val _listLocation = MutableLiveData<List<BusStationModel>>()
    val listLocation: LiveData<List<BusStationModel>> = _listLocation

    fun authentication() {
        val apiKey = BuildConfig.OLHO_VIVO_API_KEY
        authenticationRepository.authentication(apiKey, object : APIListener<Boolean>{
            override fun onSucess(result: Boolean) {
                _authentication.value = result
            }

            override fun onFailure(message: String) {
            }
        })
    }

    fun getBusStation(locale: String){
        busStationRepository.getBusStation(locale, object : APIListener<List<BusStationModel>>{
            override fun onSucess(result: List<BusStationModel>) {
                _listLocation.value = result
            }

            override fun onFailure(message: String) {

            }
        })
    }
}