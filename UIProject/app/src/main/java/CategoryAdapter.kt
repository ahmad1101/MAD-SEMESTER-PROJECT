package com.example.uiproject

// CategoryAdapter.kt
import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import android.widget.TextView

class CategoryAdapter(private val context: Context) : SpinnerAdapter {

    private val categories = listOf("Category 1", "Category 2", "Category 3")
    private val observers = mutableListOf<DataSetObserver>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = categories[position]
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = categories[position]
        return view
    }

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int): Any {
        return categories[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isEmpty(): Boolean {
        return categories.isEmpty()
    }

    override fun registerDataSetObserver(observer: DataSetObserver) {
        observers.add(observer)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver) {
        observers.remove(observer)
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }
}