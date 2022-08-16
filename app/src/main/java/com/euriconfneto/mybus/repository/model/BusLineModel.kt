package com.euriconfneto.mybus.repository.model

import com.google.gson.annotations.SerializedName

data class BusLineModel (
    @SerializedName("cl")
    val codeLine: Int,
    @SerializedName("lc")
    val circularLine: Boolean,
    @SerializedName("lt")
    val firstSign: String,
    @SerializedName("tl")
    val secondSign: Int,
    @SerializedName("sl")
    val directionLine: Int,
    @SerializedName("tp")
    val mainTerminal: String,
    @SerializedName("ts")
    val secondTerminal: String
)