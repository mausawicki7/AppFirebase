package com.wonder.appfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        Log.d("Hola", "Braian")
    }
}