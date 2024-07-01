package com.example.uiproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsFragment : Fragment() {

    private lateinit var searchView: AutoCompleteTextView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var inventory: NextFragment.Inventory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        // Initialize the search view
        searchView = view.findViewById(R.id.search_view)
        searchView.threshold = 1 // Start searching from the first character

        // Initialize the products recycler view
        productsRecyclerView = view.findViewById(R.id.products_recycler_view)
        productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the inventory
        inventory = NextFragment.Inventory(
            listOf(
                NextFragment.Product("Product 1", 10, 10.99),
                NextFragment.Product("Product 2", 20, 9.99),
                NextFragment.Product("Product 3", 30, 12.99),
                // Add more products here
            )
        )

        // Set up the search adapter
        val searchAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, inventory.products.map { it.name })
        searchView.setAdapter(searchAdapter)

        // Set up the products recycler view adapter
        val productsAdapter = ProductsAdapter(inventory.products)
        productsRecyclerView.adapter = productsAdapter

        return view
    }
}

// Define a custom adapter for the products recycler view
class ProductsAdapter(private val products: List<NextFragment.Product>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTextView: TextView = itemView.findViewById(R.id.product_name)
        val productQuantityTextView: TextView = itemView.findViewById(R.id.product_quantity)
        val productPriceTextView: TextView = itemView.findViewById(R.id.product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_products, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.productNameTextView.text = product.name
        holder.productQuantityTextView.text = "Quantity: ${product.quantity}"
        holder.productPriceTextView.text = "Price: ${product.price}"
    }

    override fun getItemCount(): Int {
        return products.size
    }
}