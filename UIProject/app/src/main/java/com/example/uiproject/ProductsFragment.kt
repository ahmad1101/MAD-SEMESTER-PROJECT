package com.example.uiproject


import android.content.Intent import android.os.Bundle import androidx.fragment.app.Fragment import android.view.LayoutInflater import android.view.View import android.view.ViewGroup import android.widget.ArrayAdapter import android.widget.AutoCompleteTextView import android.widget.TextView import androidx.appcompat.app.AppCompatActivity import androidx.recyclerview.widget.LinearLayoutManager import androidx.recyclerview.widget.RecyclerView

class ProductsFragment : Fragment() {

    private lateinit var searchView: AutoCompleteTextView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var inventory: NextFragment.Inventory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        // Initialize the search view
        searchView = view.findViewById(R.id.search_view)
        searchView.threshold = 1 // Start searching from the first character

        // Initialize the products recycler view
        productsRecyclerView = view.findViewById(R.id.products_recycler_view)
        productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the inventory
        val inventory1 = NextFragment.Inventory(
            "Beef",
            listOf(
                NextFragment.Product("Product 1", 10, 10.99),
                NextFragment.Product("Product 2", 20, 9.99),
                NextFragment.Product("Product 3", 30, 12.99),
                // Add more products here
            )
        )

        val inventory2 = NextFragment.Inventory(
            "Chicken",
            listOf(
                NextFragment.Product("Product 4", 40, 13.99),
                NextFragment.Product("Product 5", 50, 14.99),
                NextFragment.Product("Product 6", 60, 15.99),
                // Add more products here
            )
        )

        val inventory3 = NextFragment.Inventory(
            "Dairy",
            listOf(
                NextFragment.Product("Product 7", 70, 16.99),
                NextFragment.Product("Product 8", 80, 17.99),
                NextFragment.Product("Product 9", 90, 18.99),
                // Add more products here
            )
        )

        val inventories = listOf(inventory1, inventory2, inventory3)

        // Set up the search adapter
        val searchAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            inventories.flatMap { it.products }.map { it.name })
        searchView.setAdapter(searchAdapter)

        // Set up the products recycler view adapter
        val productsAdapter = InventoryAdapter(inventories, requireContext())
        productsRecyclerView.adapter = productsAdapter

        return view
    }
}