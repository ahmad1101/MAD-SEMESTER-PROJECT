package com.example.uiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        bottomNav = findViewById(R.id.bottom_nav)

        // Set the default fragment to InventoryFragment
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, InventoryFragment()).commit()

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inventory -> {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, InventoryFragment()).commit()
                    true
                }
                R.id.nav_products -> {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsFragment()).commit()
                    true
                }
                R.id.nav_pos -> {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, PosFragment()).commit()
                    true
                }
                R.id.nav_scan_product -> {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, ScanProductFragment()).commit()
                    true
                }
                R.id.nav_limited_stocks -> {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, LimitedStocksFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}