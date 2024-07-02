package com.example.uiproject

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InventoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        val addButton = view.findViewById<Button>(R.id.create_inventory_button)
        addButton.setOnClickListener {
            // Move forward to the next fragment
            val nextFragment = NextFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, nextFragment)?.addToBackStack(null)?.commit()
        }

        return view
    }
}

class NextFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var addProductButton: Button
    private lateinit var rightLinearLayout: LinearLayout
    private lateinit var inventoryRecyclerView: RecyclerView
    private lateinit var productRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventory_next, container, false)

        searchEditText = view.findViewById(R.id.search_bar)
        addProductButton = view.findViewById(R.id.add_product_button)
        rightLinearLayout = view.findViewById(R.id.right)
        inventoryRecyclerView = view.findViewById(R.id.inventory_recycler_view)
        productRecyclerView = view.findViewById(R.id.products_recycler_view)

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchForProduct()
                true
            } else {
                false
            }
        }

        searchEditText.setOnClickListener {
            searchForProduct()
        }

        addProductButton.setOnClickListener {
            showPopup()
        }

        val inventories = listOf(
            Inventory(listOf(
                Product("Product 1", 10, 10.99),
                Product("Product 2", 20, 20.99)
            )),
            Inventory(listOf(
                Product("Product 3", 30, 30.99),
                Product("Product 4", 40, 40.99)
            )),
            Inventory(listOf(
                Product("Product 5", 50, 50.99),
                Product("Product 6", 60, 60.99)
            )),
            Inventory(listOf(
                Product("Product 7", 70, 70.99),
                Product("Product 8", 80, 80.99)
            ))
        )

        inventoryRecyclerView.layoutManager = LinearLayoutManager(context)
        inventoryRecyclerView.adapter = InventoryAdapter(inventories)

        productRecyclerView.layoutManager = LinearLayoutManager(context)
        val products = listOf<Product>()
        productRecyclerView.adapter = ProductAdapter(products)

        return view
    }

    private fun searchForProduct() {
        val searchTerm = searchEditText.text.toString()
        if (searchTerm.isEmpty()) {
            Toast.makeText(
                context,
                "Invalid entry. Product could not be found!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            if (productExists(searchTerm)) {
                val productView = LayoutInflater.from(context)
                    .inflate(R.layout.product_view, rightLinearLayout, false)
                val productNameTextView = productView.findViewById<TextView>(R.id.product_name)
                val productPriceTextView = productView.findViewById<TextView>(R.id.product_price)
                val productQuantityTextView =
                    productView.findViewById<TextView>(R.id.product_quantity)

                // Set the product information
                productNameTextView.text = "Product Name"
                productPriceTextView.text = "Price: $10.99"
                productQuantityTextView.text = "Quantity: 10"

                rightLinearLayout.addView(productView)
            } else {
                Toast.makeText(context, "Product does not exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun productExists(productName: String): Boolean {
        // Implement product existence logic here
        // For now, return true if the product name is "Test Product"
        return productName == "Test Product"
    }

    private fun showPopup() {
        val inflater = LayoutInflater.from(context)
        val popupView = inflater.inflate(R.layout.add_product_popup, null)

        val nameEditText = popupView.findViewById<EditText>(R.id.name_edit_text)
        val quantityEditText = popupView.findViewById<EditText>(R.id.quantity_edit_text)
        val priceEditText = popupView.findViewById<EditText>(R.id.price_edit_text)
        val categorySpinner = popupView.findViewById<Spinner>(R.id.category_spinner)
        val saveButton = popupView.findViewById<Button>(R.id.save_button)

        val categories = arrayOf("Category 1", "Category 2", "Category 3", "Category 4")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        categorySpinner.adapter = adapter


        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val quantity = quantityEditText.text.toString().toInt()
            val price = priceEditText.text.toString().toDouble()
            val category = categorySpinner.selectedItem.toString()

            // Create a new product and add it to the list
            val product = "$name - $quantity - $price - $category"
            // Add the product to the list

            // Dismiss the popup
            (popupView.parent as ViewGroup).removeView(popupView)
        }

        // Set the layout parameters of the popupView
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.width = 500
        layoutParams.height = 400
        layoutParams.gravity = Gravity.CENTER

        // Show the popup
        val popupWindow = PopupWindow(popupView, 500, 400, true)
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)
    }

    private fun displayProducts(products: List<Product>) {
        (productRecyclerView.adapter as ProductAdapter).updateProducts(products)
    }

    private inner class InventoryAdapter(private val inventories: List<Inventory>) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.inventory_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(inventories[position])
        }

        override fun getItemCount(): Int {
            return inventories.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val inventoryTextView: TextView = itemView.findViewById(R.id.inventory_name)

            fun bind(inventory: Inventory) {
                inventoryTextView.text = "Inventory ${inventory.products.size} products"
                itemView.setOnClickListener {
                    displayProducts(inventory.products)
                }
            }
        }
    }

    private inner class ProductAdapter(private var products: List<Product>) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.product_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(products[position])
        }

        override fun getItemCount(): Int {
            return products.size
        }

        fun updateProducts(newProducts: List<Product>) {
            products = newProducts
            notifyDataSetChanged()
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val nameTextView: TextView = itemView.findViewById(R.id.product_name)
            private val quantityTextView: TextView = itemView.findViewById(R.id.product_quantity)
            private val priceTextView: TextView = itemView.findViewById(R.id.product_price)

            fun bind(product: Product) {
                nameTextView.text = product.name
                quantityTextView.text = "Quantity: ${product.quantity}"
                priceTextView.text = "Price: ${product.price}"

            }
        }
    }

    data class Product(val name: String, val quantity: Int, val price: Double)
    data class Inventory(val products: List<Product>)
}