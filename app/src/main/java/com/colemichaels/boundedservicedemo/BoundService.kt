package com.colemichaels.boundedservicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class BoundService : Service() {
    // Local class which can produce a reference to parent class.
    private val myBinder = MyLocalBinder()

    // onBind a reference to MyLocalBinder is returned for the client to interact with.
    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }

    // Bound services communicate with boundClients by passing configured Binder object to the client.
    // This subclass returns a reference to the BoundService class for the client to interact with.
    inner class MyLocalBinder : Binder() {
        fun getService() : BoundService {
            return this@BoundService
        }
    }
}