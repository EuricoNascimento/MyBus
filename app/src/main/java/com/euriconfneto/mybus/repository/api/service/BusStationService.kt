package com.euriconfneto.mybus.repository.api.service

import com.euriconfneto.mybus.repository.model.BusStationModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BusStationService {
    @GET("Parada/Buscar{termosBuscar}")
    fun findBusStation(
        @Path("termosBuscar") termosBuscar: String
    ): Call<List<BusStationModel>>

    @GET("Parada/BuscarParadasPorLinha{codigoLinha}")
    fun findBusStationByLine(
        @Path("codigoLinha") codigoLinha: String
    ): Call<List<BusStationModel>>

    @GET("Parada/BuscarParadasPorCorredo{codigoCorredor}")
    fun findBusStationByLane(
        @Path("codigoCorredor") codigoCorredor: String
    ): Call<List<BusStationModel>>

}