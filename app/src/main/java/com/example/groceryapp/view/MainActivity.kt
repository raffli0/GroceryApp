package com.example.groceryapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.groceryapp.R
import com.example.groceryapp.view.main.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.groceryapp.utils.BottomNavVisibilityListener

class MainActivity : AppCompatActivity(), BottomNavVisibilityListener {
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

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun hideBottomNav() {
        bottomNav.animate().cancel()
        bottomNav.animate()
            .translationY(bottomNav.height.toFloat())
            .setDuration(150)
            .start()
    }

    override fun showBottomNav() {
        bottomNav.animate().cancel()
        bottomNav.animate()
            .translationY(0f)
            .setDuration(150)
            .start()
    }
}
