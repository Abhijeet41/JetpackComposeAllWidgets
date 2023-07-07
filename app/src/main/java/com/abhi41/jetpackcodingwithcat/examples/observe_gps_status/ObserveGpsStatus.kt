package com.abhi41.jetpackcodingwithcat.examples.observe_gps_status

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.abhi41.jetpackcodingwithcat.R

class ObserveGpsStatus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observe_gps_status)

        val gpsStatusListener = GpsStatusListener(this)
        val turnOnGps = TurnOnGps(applicationContext)
        var isGpsStatusChanged: Boolean? = null
        gpsStatusListener.observe(this) { isGpsOn ->

            if (isGpsStatusChanged == null) {
                if (!isGpsOn) {
                    // turn on gps
                    turnOnGps.startGps(resultLauncher)
                }
                isGpsStatusChanged = isGpsOn
            } else {
                if (isGpsStatusChanged != isGpsOn) {
                    if (!isGpsOn) {
                        // turn on gps
                        turnOnGps.startGps(resultLauncher)
                    }
                    isGpsStatusChanged = isGpsOn
                }

            }

        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            Toast.makeText(applicationContext, "Gps is on", Toast.LENGTH_SHORT).show()
        } else if (activityResult.resultCode == RESULT_CANCELED) {
            Toast.makeText(applicationContext, "Request is Canceled", Toast.LENGTH_SHORT).show()
        }
    }
}