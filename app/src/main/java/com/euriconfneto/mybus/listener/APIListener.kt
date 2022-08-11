package com.euriconfneto.mybus.listener

interface APIListener <T> {
    fun onSucess(result: T)

    fun onFailure(message: String)
}