package com.euriconfneto.mybus.repository

import com.euriconfneto.mybus.R
import com.euriconfneto.mybus.listener.APIListener
import com.euriconfneto.mybus.repository.api.service.AuthenticationService
import com.euriconfneto.mybus.repository.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationRepository() {
    private val remote = RetrofitClient.getService(AuthenticationService::class.java)

    fun authentication(apiKey: String, listener: APIListener<Boolean>){
        val call = remote.login(apiKey)
        call.enqueue(object: Callback<Boolean>{
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
               response.body()?.let { listener.onSucess(it) }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(R.string.error_authentication.toString())
            }

        })
    }
}