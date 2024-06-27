package com.example.uiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class CashierActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cashier)

        bottomNav = findViewById(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_products -> {
                    // Open Products screen
                    true
                }
                R.id.nav_pos -> {
                    // Open POS screen
                    true
                }
                R.id.nav_scan_product -> {
                    // Open Scan Product screen
                    true
                }
                R.id.nav_inventory -> {
                    // Open Inventory screen
                    true
                }
                R.id.nav_limited_stocks -> {
                    // Open Limited Stocks screen
                    true
                }
                else -> false
            }
        }
    }
}