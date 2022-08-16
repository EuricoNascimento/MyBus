package com.euriconfneto.mybus.ui.lane

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.euriconfneto.mybus.listener.APIListener
import com.euriconfneto.mybus.repository.AuthenticationRepository
import com.euriconfneto.mybus.repository.LineRepository
import com.euriconfneto.mybus.repository.model.BusLineModel

class LaneViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()
    private val lineRepository = LineRepository()
    private val _busLines = MutableLiveData<List<BusLineModel>>()
    val busLines: LiveData<List<BusLineModel>> = _busLines

    fun getBusLine(line: String, direction: Byte = 0){
        if (direction.equals(0)){
            lineRepository.getBusLine(line, object: APIListener<List<BusLineModel>>{
                override fun onSucess(result: List<BusLineModel>) {
                   _busLines.value = result
                }

                override fun onFailure(message: String) {
                }
            })
        } else {
            lineRepository.getBusLineByDirection(
                line,
                direction,
                object: APIListener<List<BusLineModel>>{
                    override fun onSucess(result: List<BusLineModel>) {
                        _busLines.value = result
                    }

                    override fun onFailure(message: String) {
                    }
                })
        }
    }
}