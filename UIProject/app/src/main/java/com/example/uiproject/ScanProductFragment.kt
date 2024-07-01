package com.example.uiproject

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ScanProductFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var saveButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_scan_product, container, false)

        imageView = view.findViewById(R.id.image_view)
        saveButton = view.findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            // Open the camera to scan the QR code
            openCamera()
        }

        return view
    }

    private fun openCamera() {
        // Request camera permission
        requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 101)

        // Open the camera
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 102)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(bitmap)

            // Scan the QR code
//            val qrCode = scanQRCode(bitmap)
//
//            if (qrCode != null) {

//                // Check if the product exists
//                if (productExists(qrCode)) {
//                    // Display the product
//                    displayProduct(qrCode)
//                } else {
//                    Toast.makeText(context, "The product does not exist", Toast.LENGTH_SHORT).show()
//                }
//            }
        }
    }

//    private fun scanQRCode(bitmap: Bitmap): String? {
//        // Implement your QR code scanning logic here
//        // For example, you can use the ZXing library
////        val qrCodeReader = QRCodeReader()
////        val result = qrCodeReader.decode(bitmap)
////        return result.text
//    }
//
//    private fun productExists(qrCode: String): Boolean {
//        // Implement your product existence checking logic here
//        // For example, you can check a database or a web service
//        return true // Replace with your logic
//    }

    private fun displayProduct(qrCode: String) {
        // Implement your product displaying logic here
        // For example, you can display the product details in a TextView
        val textView = view?.findViewById<TextView>(R.id.product_text_view)
        textView?.text = "Product: $qrCode"
    }
}