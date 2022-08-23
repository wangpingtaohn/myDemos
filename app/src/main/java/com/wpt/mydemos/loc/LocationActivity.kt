package com.wpt.mydemos.loc

import android.content.Context
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() {

    private var mLocationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        initLoc()
    }

    private fun initLoc(){
        mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        try {
            if (mLocationManager!!.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
                mLocationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, mLocationListener)
            }
            if (mLocationManager!!.getProvider(LocationManager.GPS_PROVIDER) != null) mLocationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0f, mLocationListener
            )
        } catch (e: SecurityException) {
            Log.e("===wpt===",e.message!!)
        }
    }

    private val mLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val geocoder = Geocoder(this@LocationActivity)
            val list = geocoder.getFromLocation(location.latitude,location.longitude,1)
            val sb = StringBuffer()
            val address = list[0]
            sb.append(address.countryName)
                .append(address.locality)
                .append(address.subLocality)
            tvLoc.text = String.format("经度:%s,纬度:%s,地址:%s",location.latitude,location.longitude,address.getAddressLine(0))
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }
}