package com.example.canary

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.util.Log
import com.navigine.idl.java.NavigineSdk
import com.navigine.sdk.Navigine

class MainActivity : AppCompatActivity() {

    private lateinit var mNavigineSdk: NavigineSdk
    private val mUserHash = "A9A7-BAAC-6B7F-313D" // Replace with actual hash
    private val mLocationServer = "https://ips.navigine.com" // Or your custom server

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Navigine
        try {
            Navigine.initialize(applicationContext)

            mNavigineSdk = NavigineSdk.getInstance()
            mNavigineSdk.setUserHash(mUserHash)
            mNavigineSdk.setServer(mLocationServer)

            Log.d("Navigine", "Navigine SDK initialized successfully.")
        } catch (e: Exception) {
            Log.e("Navigine", "Error initializing Navigine SDK: ${e.message}")
        }
    }
}
