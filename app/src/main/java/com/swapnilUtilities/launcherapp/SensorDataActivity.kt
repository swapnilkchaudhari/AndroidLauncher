package com.swapnilUtilities.launcherapp

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.swapnilutilities.aidllibrary.IOrientationService
import com.swapnilutilities.aidllibrary.OrientationService
import kotlinx.android.synthetic.main.sensor_data.*

class SensorDataActivity : AppCompatActivity() {

    private var orientationService: IOrientationService? = null
    val uiUpdateDelayMS = 1500L

    val handler = Handler()

    private val runnable: Runnable = object : Runnable {

        override fun run() {
            val orientation = orientationService?.orientation
            tvPitchValue.text = orientation?.get(0)?.toString()
            tvRollValue.text = orientation?.get(1)?.toString()
            handler.postDelayed(this, uiUpdateDelayMS)
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            orientationService = IOrientationService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            orientationService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sensor_data)
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, OrientationService::class.java)
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
        startUiUpdation()
    }

    override fun onPause() {
        super.onPause()
        unbindService(serviceConnection)
        stopUiUpdation()
    }

    fun startUiUpdation() {
        handler.postDelayed(runnable, uiUpdateDelayMS)
    }

    fun stopUiUpdation() {
        handler.removeCallbacks(runnable)
    }
}