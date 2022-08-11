package com.euriconfneto.mybus.repository.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){
    companion object{
        private lateinit var INSTANCE: Retrofit
        private fun getRetrofitInstance(): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class){
                    INSTANCE = Retrofit.Builder()
                        .baseUrl("http://api.olhovivo.sptrans.com.br/v2.1/")
                        .client(okHttpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T{
            return getRetrofitInstance().create(serviceClass)
        }
    }
}