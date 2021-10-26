package com.wonder.appfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.wonder.appfirebase.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signUpButton.setOnClickListener {
            signIn()
        }

        binding.logInButton.setOnClickListener {

        }
    }

    private fun logIn() {
        if (binding.editTextTextEmailAddress.text.isNotEmpty()
            && binding.editTextTextPassword.text.isNotEmpty()) {
            val email = binding.editTextTextEmailAddress.text.toString()
            val pass = binding.editTextTextPassword.text.toString()

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
        }
    }

    private fun signIn() {
        if (binding.editTextTextEmailAddress.text.isNotEmpty()
            && binding.editTextTextPassword.text.isNotEmpty()) {
            val email = binding.editTextTextEmailAddress.text.toString()
            val pass = binding.editTextTextPassword.text.toString()

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Error")
            setMessage("Error autenticando el usuario")
            setPositiveButton("Aceptar", null)
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}