package com.wd.spending.ext

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

fun Context.isGooglePlayServicesAvailable(): Boolean {
    val connectionStatusCode: Int = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
    return connectionStatusCode == ConnectionResult.SUCCESS
}