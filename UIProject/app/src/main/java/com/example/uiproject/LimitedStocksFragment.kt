package com.example.uiproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class LimitedStocksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_limited_stocks, container, false)

        val textView = view.findViewById<TextView>(R.id.limited_stocks_text)
        textView.text = "Welcome to Limited Stocks!"

        return view
    }
}