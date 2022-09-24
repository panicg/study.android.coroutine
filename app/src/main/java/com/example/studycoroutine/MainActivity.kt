package com.example.studycoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.studycoroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var viewDataBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewDataBinding.run {
            /*
            코루틴 안에 코루틴을 넣을 수 있음
            이렇게 별 다른 작업없이 단순히 코루틴을 여러개 실행하게되면 병렬적으로 동시에 시작한다.
             */
            btn1.setOnClickListener {
                CoroutineScope(Dispatchers.Default).launch {
                    val job1 = launch {
                        for (index in 0 .. 10){
                            Log.d("job_1", index.toString())
                            delay(1)
                        }
                    }
                    val job2 = launch {
                        for (index in 0 .. 10){
                            Log.d("job_2", index.toString())
                            delay(1)
                        }
                    }
                }
            }

            /*
            병렬적으로 실행되는 코루틴의 job에 cancel을 걸면 해당 job은 delay가 있는경우 종료를 하게 된다.
             */
            btn2.setOnClickListener {
                CoroutineScope(Dispatchers.Default).launch {
                    val job1 = launch {
                        for (index in 0 .. 10){
                            Log.d("job_1", index.toString())
                            delay(1)
                        }
                    }
                    val job2 = launch {
                        for (index in 0 .. 10){
                            Log.d("job_2", index.toString())
                            delay(1)
                        }
                    }
                    job2.cancel()
                }
            }

            /*
            순서를 정할 수 있다. 다음과 같이 job1에 join을 걸었다면 job2는 job1이 끝나길 기다린 후 시작하게 된다.
             */
            btn3.setOnClickListener {
                CoroutineScope(Dispatchers.Default).launch {
                    val job1 = launch {
                        for (index in 0 .. 10){
                            Log.d("job_1", index.toString())
                            delay(1)
                        }
                    }.join()
                    val job2 = launch {
                        for (index in 0 .. 10){
                            Log.d("job_2", index.toString())
                            delay(1)
                        }
                    }
                }
            }
        }
    }
}