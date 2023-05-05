package com.example.flowdemo2compose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val myFlow = flow<Int> {
        for (i in 1..100) {
            emit(i)
            delay((1000L))
        }
    }
    private fun backPressureDemo(){
        val myFlow1 = flow<Int> {
            for (i in 1..10) {
                Log.i("MYTAG", "Producido $i")
                emit(i)
                delay((1000L))
            }
        }
        viewModelScope.launch {
            myFlow1.collect{
                delay((2000L))
                Log.i("MYTAG", "Consumido $it")
            }
        }
    }
    init {
        backPressureDemo()
    }


}