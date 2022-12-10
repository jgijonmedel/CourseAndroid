package com.jimmy.courseandroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.jimmy.courseandroid.databinding.ActivityMainBinding
import com.jimmy.courseandroid.fragments.LoginFragment.Companion.IS_AUTH_PREFERENCE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Ciclos de vida", "Método on CREATE")
        setUpNavigationGraph()
    }

    /**
     * 1) host -> 2) controller -> 3) graph -> 4) destination
     */
    private fun setUpNavigationGraph() {
        // 1
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        // 2
        val navController = navHostFragment.navController
        // 3
        val navGraph = navController.navInflater.inflate(R.navigation.navigation_main)
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val isAuth = sharedPreferences.getBoolean(IS_AUTH_PREFERENCE, false)
        val destination = if(isAuth) R.id.charactersDbzFragment else R.id.loginFragment
        // 4
        navGraph.setStartDestination(destination)
        navController.graph = navGraph
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

}