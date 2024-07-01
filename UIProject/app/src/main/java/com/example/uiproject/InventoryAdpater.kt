package com.example.uiproject.com.example.uiproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uiproject.NextFragment
import com.example.uiproject.R

class InventoryAdapter(private val inventories: List<NextFragment.Inventory>, private val onItemClick: (NextFragment.Inventory) -> Unit) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

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
        private val inventoryTextView: TextView = itemView.findViewById(R.id.inventory_text_view)

        fun bind(inventory: NextFragment.Inventory) {
            inventoryTextView.text = "Inventory ${inventory.products.size} products"
            itemView.setOnClickListener { onItemClick(inventory) }
        }
    }
}