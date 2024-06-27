package com.example.uiproject

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class CreateInventoryActivity : AppCompatActivity() {

    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var addProductButton: Button
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var productsAdapter: ProductsAdapter
    private val products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_inventory)

        productsRecyclerView = findViewById(R.id.products_recycler_view)
        addProductButton = findViewById(R.id.add_product_button)
        searchView = findViewById(R.id.search_view)

        productsAdapter = ProductsAdapter(products)
        productsRecyclerView.adapter = productsAdapter
        productsRecyclerView.layoutManager = LinearLayoutManager(this)

        addProductButton.setOnClickListener {
            // Show the popup to add a product
            showAddProductPopup()
        }
    }

    private fun showAddProductPopup() {
        // Inflate the popup layout
        val popupView = LayoutInflater.from(this).inflate(R.layout.popup_add_product, null)

        // Get the views from the popup layout
        val categorySpinner = popupView.findViewById<android.widget.Spinner>(R.id.category_spinner)
        val productNameEditText = popupView.findViewById<android.widget.EditText>(R.id.name_edit_text)
        val productQuantityEditText = popupView.findViewById<android.widget.EditText>(R.id.quantity_edit_text)
        val productPriceEditText = popupView.findViewById<android.widget.EditText>(R.id.price_edit_text)
        val addButton = popupView.findViewById<Button>(R.id.save_button)

        // Create an adapter for the category spinner
        val categoryAdapter = CategoryAdapter(this)
        categorySpinner.adapter = categoryAdapter

        // Show the popup
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

        // Add a product when the add button is clicked
        addButton.setOnClickListener {
            // Get the product details from the popup
            val categoryName = categorySpinner.selectedItem.toString()
            val productName = productNameEditText.text.toString()
            val productQuantity = productQuantityEditText.text.toString().toInt()
            val productPrice = productPriceEditText.text.toString().toDouble()

            // Create a new product
            val product = Product(productName, productQuantity, productPrice, categoryName)

            // Add the product to the list
            products.add(product)
            productsAdapter.notifyDataSetChanged()

            // Dismiss the popup
            popupWindow.dismiss()
        }
    }
}