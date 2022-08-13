package com.euriconfneto.mybus.repository.api.service

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthenticationService {
    @POST("Login/Autenticar")
    fun login(@Query("token")token: String): Call<Boolean>
}