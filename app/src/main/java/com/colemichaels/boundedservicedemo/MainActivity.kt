package com.colemichaels.boundedservicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.colemichaels.boundedservicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private var myService: BoundService? = null
    private var isBound = false

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundService.MyLocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
        binding.button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        showTime()
    }

    private fun showTime() {
        binding.textView.text = myService?.getCurrentTime()
    }
}