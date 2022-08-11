package com.euriconfneto.mybus.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.euriconfneto.mybus.BuildConfig
import com.euriconfneto.mybus.listener.APIListener
import com.euriconfneto.mybus.repository.AuthenticationRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val authenticationRepository = AuthenticationRepository()
    private val _authentication = MutableLiveData<Boolean>()
    val authentication: LiveData<Boolean> = _authentication

    fun authentication() {
        val apiKey = BuildConfig.OLHO_VIVO_API_KEY
        authenticationRepository.authentication(apiKey, object : APIListener<Boolean>{
            override fun onSucess(result: Boolean) {
                _authentication.value = result
            }

            override fun onFailure(message: String) {
            }
        })
    }
}