package com.euriconfneto.mybus.repository.api.service

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthenticationService {
    @POST("Login/Autenticar{token}")
    fun login(@Path("token")token: String): Call<Boolean>
}