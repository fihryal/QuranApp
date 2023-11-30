package com.faqiy.quran.core.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import java.util.Locale

class LocationPreferences(val context: Context) {
    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getKnownLastLocation(): LiveData<List<String>> {
        val lastKnownLocation = MutableLiveData<List<String>>()
        fusedLocation.lastLocation.addOnSuccessListener { location ->
            val geocoder = Geocoder(context, Locale.getDefault())
            if (Build.VERSION.SDK_INT >= 33) {
                geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                ) { listAddress ->
                    val city = listAddress[0].subAdminArea
                    val arrCity = city.split(" ")
                    val subLocality = listAddress[0].subLocality
                    val locality = listAddress[0].locality
                    val resultLocation = "$subLocality, $locality"


                    val currentLanguage = Locale.getDefault().language.toString()
                    Log.i("LocPref", "getCurrentLanguage: $currentLanguage")

                    val cityResult: String = when (currentLanguage) {

                        "in" -> cityResult(false, arrCity)
                        "en" -> cityResult(true, arrCity)

                        else -> {
                            Log.e("LocPref", "error: current language is undefined.")
                            "Jakarta"
                        }
                    }

                    val listCity = listOf(arrCity[0], resultLocation)
                    Log.i("LocPref", "data: $listCity")
                    lastKnownLocation.postValue(listCity)
                }
            } else {
                val listAddress = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )
                val city = listAddress?.get(0)?.subAdminArea
                val arrCity = city?.split(" ")
                val subLocality = listAddress?.get(0)?.subLocality
                val locality = listAddress?.get(0)?.locality
                val resultLocation = "$subLocality, $locality"

                val currentLanguage = Locale.getDefault().language.toString()
                Log.i("LocPref", "getCurrentLanguage: $currentLanguage")

                val cityResult: String = if (arrCity != null) {
                    when (Locale.getDefault().language) {

                        "in" -> cityResult(false, arrCity)
                        "en" -> cityResult(true, arrCity)

                        else -> {
                            Log.e("LocPref", "error: current language is undefined.")
                            "Jakarta"
                        }
                    }
                } else {
                    Log.e("LocPref", "error: current language is undefined.")
                    "Jakarta"
                }

                val listCity = listOf(cityResult, resultLocation)
                Log.i("LocPref", "data: $listCity")
                lastKnownLocation.postValue(listCity)
            }

        }

        fusedLocation.lastLocation.addOnFailureListener {
            Log.e("SharedViewModel", "getKnownLastLocation: " + it.localizedMessage)
        }
        return lastKnownLocation
    }

    private fun cityResult(isEnglish: Boolean, arrCity: List<String>): String {
        var result = ""
        if (isEnglish) {
            for (i in 0 until arrCity.size - 1) {
                result += arrCity[i] + " "
            }

        } else {
            for (i in 1 until arrCity.size) {
                result += arrCity[i] + " "
            }
        }
        return result
    }
}