package com.narara.android_stop_watch_kotlin_exam

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class StopWatchViewModel : ViewModel() {
    val time : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    var isRunning = MutableLiveData<Boolean>()

    init { // 초기화 블럭 , 생성자에서 하는 것을 여기서 하면 된다
        time.value = 0
        isRunning.value = false
    }
    private var timerTask: Timer? = null

    private fun pause() {
        timerTask?.cancel()
    }

    private fun start() {
        timerTask = timer(period = 10) {
            // Background에서 LiveData값 갱신할 때는 postValue를 써야 한다
            time.postValue(time.value?.plus(1))
        }

    }

    fun onStartButtonClicked() {
        isRunning.value = !isRunning.value!!
        if (isRunning.value!!) {
            start()
        } else {
            pause()
        }
    }

    override fun onCleared() {
        pause()
        timerTask?.cancel()
        super.onCleared()
    }
}