package com.example.uiproject

import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.fragment.app.Fragment

class InventoryFragment : Fragment() {

    private var inventories = mutableListOf<Inventory>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        val createInventoryButton = view.findViewById<Button>(R.id.create_inventory_button)
        createInventoryButton.setOnClickListener {
            showCreateInventoryPopup()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.inventory_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = InventoryAdapter(inventories)

        return view
    }

    private fun showCreateInventoryPopup() {
        val inflater = LayoutInflater.from(context)
        val popupView = inflater.inflate(R.layout.popup_add_product, null)

        val categorySpinner = popupView.findViewById<Spinner>(R.id.category_spinner)
        val adapter = CategoryAdapter(requireContext())
        categorySpinner.adapter = adapter

        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

        val saveButton = popupView.findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            // Get the input values from the popup
            val nameEditText = popupView.findViewById<EditText>(R.id.name_edit_text)
            val quantityEditText = popupView.findViewById<EditText>(R.id.quantity_edit_text)
            val priceEditText = popupView.findViewById<EditText>(R.id.price_edit_text)
            val categorySpinner = popupView.findViewById<Spinner>(R.id.category_spinner)

            val name = nameEditText.text.toString()
            val quantity = quantityEditText.text.toString().toInt()
            val price = priceEditText.text.toString().toDouble()
            val category = categorySpinner.selectedItem.toString()

            // Create and save the inventory
            val inventory = Inventory(name, quantity, price, category)
            inventories.add(inventory)

            // Update the RecyclerView
            val recyclerView = view?.findViewById<RecyclerView>(R.id.inventory_recycler_view)
            recyclerView?.adapter?.notifyDataSetChanged()

            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }
}

data class Inventory(val name: String, val quantity: Int, val price: Double, val category: String)
