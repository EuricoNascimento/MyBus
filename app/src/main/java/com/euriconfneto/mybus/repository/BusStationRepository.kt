package com.euriconfneto.mybus.repository

import com.euriconfneto.mybus.R
import com.euriconfneto.mybus.listener.APIListener
import com.euriconfneto.mybus.repository.api.RetrofitClient
import com.euriconfneto.mybus.repository.api.service.BusStationService
import com.euriconfneto.mybus.repository.model.BusStationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusStationRepository {
    private val remote = RetrofitClient.getService(BusStationService::class.java)

    fun getBusStation(locale: String, listener: APIListener<List<BusStationModel>>){
        val call = remote.findBusStation(locale)
        call.enqueue(object: Callback<List<BusStationModel>>{
            override fun onResponse(
                call: Call<List<BusStationModel>>,
                response: Response<List<BusStationModel>>
            ) {
                response.body()?.let { listener.onSucess(it) }
            }

            override fun onFailure(call: Call<List<BusStationModel>>, t: Throwable) {
                listener.onFailure(R.string.error_authentication.toString())
            }
        })
    }
}