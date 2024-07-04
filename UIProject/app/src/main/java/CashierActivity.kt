package com.example.uiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uiproject.NextFragment.Product

class CashierActivity : AppCompatActivity() {

    // Declare variables for the bottom navigation view and the products recycler view
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    // Create a list to store the products
    private val productsList = mutableListOf<Product>()

    // Create a list to store the products in the POS
    private val posProductsList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cashier)

        // Initialize the bottom navigation view
        bottomNav = findViewById(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_products -> {
                    // Open Products screen
                    displayProducts()
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

        // Initialize the products recycler view
        productsRecyclerView = findViewById(R.id.products_recycler_view)
        productsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Add some sample products to the products list
        productsList.add(Product("Product 1", 10, 10.99))
        productsList.add(Product("Product 2", 20, 20.99))
        productsList.add(Product("Product 3", 30, 30.99))
        productsList.add(Product("Product 4", 40, 40.99))
        productsList.add(Product("Product 5", 50, 50.99))
        productsList.add(Product("Product 6", 60, 60.99))
        productsList.add(Product("Product 7", 70, 70.99))
        productsList.add(Product("Product 8", 80, 80.99))

        // Create an instance of the ProductAdapter and set it to the products recycler view
        productAdapter = ProductAdapter(productsList)
        productsRecyclerView.adapter = productAdapter
    }

    // Function to display the products
    private fun displayProducts() {
        // Show the products RecyclerView
        productsRecyclerView.visibility = View.VISIBLE
    }

    // Inner class for the ProductAdapter
    private inner class ProductAdapter(private val products: List<Product>) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.product_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(products[position])
        }

        override fun getItemCount(): Int {
            return products.size
        }

        // Inner class for the ViewHolder
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val nameTextView: TextView = itemView.findViewById(R.id.product_name)
            private val quantityTextView: TextView = itemView.findViewById(R.id.product_quantity)
            private val priceTextView: TextView = itemView.findViewById(R.id.product_price)

            init {
                // Set an on-click listener for the product item
                itemView.setOnClickListener {
                    // Get the product at the current position
                    val product = products[adapterPosition]

                    // Add the product to the POS products list
                    posProductsList.add(product)

                    // Display a toast message to indicate that the product has been added to the POS
                    Toast.makeText(itemView.context, "Product added to POS", Toast.LENGTH_SHORT).show()
                }
            }

            fun bind(product: Product) {
                nameTextView.text = product.name
                quantityTextView.text = "Quantity: ${product.quantity}"
                priceTextView.text = "Price: ${product.price}"
            }
        }
    }

    // Data class for the Product
    data class Product(val name: String, val quantity: Int, val price: Double)
}