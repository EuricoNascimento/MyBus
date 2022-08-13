package com.euriconfneto.mybus.repository.api.service

import com.euriconfneto.mybus.repository.model.BusStationModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BusStationService {
    @GET("Parada/Buscar")
    fun findBusStation(
        @Query("termosBuscar") termosBuscar: String
    ): Call<List<BusStationModel>>

    @GET("Parada/BuscarParadasPorLinha")
    fun findBusStationByLine(
        @Query("codigoLinha") codigoLinha: String
    ): Call<List<BusStationModel>>

    @GET("Parada/BuscarParadasPorCorredoA")
    fun findBusStationByLane(
        @Query("codigoCorredor") codigoCorredor: String
    ): Call<List<BusStationModel>>

}