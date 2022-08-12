package com.euriconfneto.mybus.repository.model

import com.google.gson.annotations.SerializedName

data class BusStationModel (
    @SerializedName("cp")
    val stationCode: Int,
    @SerializedName("np")
    val stationName: String,
    @SerializedName("ep")
    val stationAddress: String,
    @SerializedName("py")
    val stationLatitude: Double,
    @SerializedName("px")
    val stationLongitude: Double
        )