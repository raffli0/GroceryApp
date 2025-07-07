package com.example.groceryapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.groceryapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //point bottomnav by id
        bottomNav = findViewById(R.id.bottomNavigationView)

        // Load HomeFragment pertama kali
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
//                R.id.nav_explore -> {
//                    loadFragment(ExploreFragment()) // Bisa dummy dulu
//                    true
//                }
//                R.id.nav_cart -> {
//                    loadFragment(CartFragment()) // Bisa dummy dulu
//                    true
//                }
//                R.id.nav_profile -> {
//                    loadFragment(ProfileFragment()) // Bisa dummy dulu
//                    true
//                }
                else -> false
            }
        }
    }

    fun hideBottomNav() {
        Log.d("MainActivity", "hideBottomNav called")
        bottomNav.animate().translationY(bottomNav.height.toFloat()).setDuration(200).start()
    }

    fun showBottomNav() {
        Log.d("MainActivity", "showBottomNav called")
        bottomNav.animate().translationY(0f).setDuration(200).start()
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
