package com.example.uiproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InventoryAdapter(private val inventories: List<Inventory>) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.inventory_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inventory = inventories[position]
        holder.nameTextView.text = inventory.name
        holder.quantityTextView.text = inventory.quantity.toString()
        holder.priceTextView.text = inventory.price.toString()
        holder.categoryTextView.text = inventory.category
    }

    override fun getItemCount(): Int {
        return inventories.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.name_text_view)
        val quantityTextView = itemView.findViewById<TextView>(R.id.quantity_text_view)
        val priceTextView = itemView.findViewById<TextView>(R.id.price_text_view)
        val categoryTextView = itemView.findViewById<TextView>(R.id.category_text_view)
    }
}