package com.example.uiproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

class InventoryAdapter(private val inventories: List<NextFragment.Inventory>, private val context: Context) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

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

        fun bind(inventory: NextFragment.Inventory) {
            val productNames = inventory.products.joinToString { it.name }
            inventoryTextView.text = "$productNames \n"
            itemView.setOnClickListener {
                val fragment = ProductsFragment()
                val bundle = Bundle()
                bundle.putInt("inventory_id", position)
                fragment.arguments = bundle
                val fragmentManager: FragmentManager = (context as FragmentActivity).supportFragmentManager
                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }
}