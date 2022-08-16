package com.euriconfneto.mybus.repository.api.service

import com.euriconfneto.mybus.repository.model.BusLineModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LineService {
    @GET("Linha/Buscar")
    fun findLine(@Query("termosBusca") line:String): Call<List<BusLineModel>>
    @GET("Linha/Buscar")
    fun findLineByDirection(
        @Query("termosBusca") line:String,
        @Query("sentido") direction:Byte
    ): Call<List<BusLineModel>>
}