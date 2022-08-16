package com.euriconfneto.mybus.repository

import com.euriconfneto.mybus.R
import com.euriconfneto.mybus.listener.APIListener
import com.euriconfneto.mybus.repository.api.RetrofitClient
import com.euriconfneto.mybus.repository.api.service.LineService
import com.euriconfneto.mybus.repository.model.BusLineModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LineRepository {
    private val remote = RetrofitClient.getService(LineService::class.java)

    fun getBusLine(line: String, listener: APIListener<List<BusLineModel>>){
        val call = remote.findLine(line)
        call.enqueue(object : Callback<List<BusLineModel>>{
            override fun onResponse(
                call: Call<List<BusLineModel>>,
                response: Response<List<BusLineModel>>
            ) {
                response.body()?.let { listener.onSucess(it) }
            }

            override fun onFailure(call: Call<List<BusLineModel>>, t: Throwable) {
                listener.onFailure(R.string.error_authentication.toString())
            }
        })
    }

    fun getBusLineByDirection(
        line: String,
        direction: Byte,
        listener: APIListener<List<BusLineModel>>
    ){
        val call =remote.findLineByDirection(line, direction)
        call.enqueue(object: Callback<List<BusLineModel>>{
            override fun onResponse(
                call: Call<List<BusLineModel>>,
                response: Response<List<BusLineModel>>
            ) {
                response.body()?.let { listener.onSucess(it) }
            }

            override fun onFailure(call: Call<List<BusLineModel>>, t: Throwable) {
                listener.onFailure(R.string.error_authentication.toString())
            }

        })
    }
}