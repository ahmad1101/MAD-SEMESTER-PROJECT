package com.example.uiproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter(private val products: MutableList<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.nameTextView.text = product.name
        holder.quantityTextView.text = product.quantity.toString()
        holder.priceTextView.text = product.price.toString()
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.product_name_text_view)
        val quantityTextView: TextView = itemView.findViewById(R.id.product_quantity_text_view)
        val priceTextView: TextView = itemView.findViewById(R.id.product_price_text_view)
    }
}

// Product.kt
data class Product(val name: String, val quantity: Int, val price: Double, val category: String)