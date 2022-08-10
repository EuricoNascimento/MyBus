package com.euriconfneto.mybus.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){
    companion object{
        private lateinit var instance: Retrofit
        private fun getRetrofitInstance(): Retrofit {
            val okHttpClient = OkHttpClient.Builder()

            if (!::instance.isInitialized) {
                synchronized(RetrofitClient::class){
                    instance = Retrofit.Builder()
                        .baseUrl("http://api.olhovivo.sptrans.com.br/v2.1/")
                        .client(okHttpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return instance
        }

        fun <T> getService(serviceClass: Class<T>): T{
            return getRetrofitInstance().create(serviceClass)
        }

    }
}