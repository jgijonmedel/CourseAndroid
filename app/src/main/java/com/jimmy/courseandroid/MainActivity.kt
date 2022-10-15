package com.jimmy.courseandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.jimmy.courseandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowResult.setOnClickListener {
            val nameString = binding.etName.text.toString()
            val lastNameString = binding.etLastName.text.toString()
            val fullName =  getFullName(name = nameString, lastName = lastNameString)
            binding.tvResult.text = fullName

            Toast.makeText(this, fullName, Toast.LENGTH_SHORT).show()
        }

        Log.d("Ciclos de vida", "Método on CREATE")

        binding.tvResult.setOnClickListener {
            goToSecondActivity()
        }
    }

    private fun goToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Ciclos de vida", "Método on START")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Ciclos de vida", "Método on RESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Ciclos de vida", "Método on PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Ciclos de vida", "Método on STOP")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Ciclos de vida", "Método on RESTART")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Ciclos de vida", "Método on DESTROY")
        Toast.makeText(this, "Mensaje", Toast.LENGTH_SHORT).show()
    }

    private fun getFullName(
        name: String,
        lastName: String
    ): String {
        return "mi nombre completo es: $name $lastName"
    }


}