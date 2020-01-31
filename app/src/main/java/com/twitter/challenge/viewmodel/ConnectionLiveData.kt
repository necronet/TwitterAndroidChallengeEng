package com.twitter.challenge.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData

class ConnectionLiveData(val context:Context) : LiveData<Boolean>() {

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val extras = intent?.extras
            val info = extras?.getParcelable<NetworkInfo>("networkInfo")
            postValue(info?.state == NetworkInfo.State.CONNECTED)
        }
    }

    override fun onActive() {
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
    }

    override fun onInactive() {
        context.unregisterReceiver(networkReceiver)
    }

}