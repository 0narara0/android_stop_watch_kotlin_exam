package com.narara.android_stop_watch_kotlin_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(StopWatchViewModel::class.java)

        viewModel.time.observe(this, androidx.lifecycle.Observer { time ->
            val sec = time / 100
            val milli = time % 100

            sec_text.text = "$sec"
            milli_text.text = "$milli"

        })

        viewModel.isRunning.observe(this, androidx.lifecycle.Observer { isRunning ->
            if (isRunning) {
                start_button.text = "Pause"
            } else {
                start_button.text = "Start"
            }
        })

        // 1000 -> 1초에 한번씩

        start_button.setOnClickListener {
            viewModel.onStartButtonClicked()
        }
    }


}
