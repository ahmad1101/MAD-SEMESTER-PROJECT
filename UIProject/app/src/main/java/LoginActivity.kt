package com.example.uiproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val users = mutableMapOf<String, User>()

    // Default Users
    init {
        users["cashier"] = User("cashier", "cashier@gmail.com", "cashier123")
        users["admin"] = User("admin", "admin@gmail.com", "admin123")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val signUpTextView = findViewById<TextView>(R.id.signUpTextView)

        // Event Listener - SignUp Text
        signUpTextView.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Sign Up")
                .setMessage("Enter your details to sign up")
                .setView(R.layout.sign_up_dialog)
                .create() //Inflate SignUp Dialogue Box

            alertDialog.show()

            val signUpUsernameEditText = alertDialog.findViewById<EditText>(R.id.signUpUsernameEditText)
            val signUpEmailEditText = alertDialog.findViewById<EditText>(R.id.signUpEmailEditText)
            val signUpPasswordEditText = alertDialog.findViewById<EditText>(R.id.signUpPasswordEditText)
            val signUpBtn = alertDialog.findViewById<Button>(R.id.signUpBtn)

            // Event Listener - SignUp Button - SignUp Dialogue Box
            signUpBtn!!.setOnClickListener {
                val username = signUpUsernameEditText!!.text.toString()
                val email = signUpEmailEditText!!.text.toString()
                val password = signUpPasswordEditText!!.text.toString()

                // Validations for UserName and EmailAddress and Password - SignUp Dialogue Box
                if (username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
                    AlertDialog.Builder(this)
                        .setMessage("Invalid input. Pls fill in all the fields")
                        .setPositiveButton("OK") { _, _ -> }
                        .create()
                        .show()
                } else if (!isValidEmail(email)) {
                    AlertDialog.Builder(this)
                        .setMessage("Invalid email address")
                        .setPositiveButton("OK") { _, _ -> }
                        .create()
                        .show()
                } else if (users.containsKey(username)) {
                    AlertDialog.Builder(this)
                        .setMessage("Username already exists")
                        .setPositiveButton("OK") { _, _ -> }
                        .create()
                        .show()
                } else {
                    // Create a new user and add it to the users map
                    val newUser = User(username, email, password)
                    users[username] = newUser
                    alertDialog.dismiss()
                    AlertDialog.Builder(this)
                        .setMessage("Sign up successful!")
                        .setPositiveButton("OK") { _, _ -> }
                        .create()
                        .show()
                }
            }
        }

        // Event Listener - Login Button
        loginBtn.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Login for Default Users and newly created users
            if (users.containsKey(username) && users[username]!!.password == password) {
                if (username == "cashier") {
                    val intent = Intent(this, CashierActivity::class.java)
                    startActivity(intent)
                } else if (username == "admin") {
                    val intent = Intent(this, AdminActivity::class.java)
                    startActivity(intent)
                } else {
                    // Start a new activity for newly created users
                    val intent = Intent(this, CashierActivity::class.java)
                    startActivity(intent)
                }
            } else {
                AlertDialog.Builder(this)
                    .setMessage("Invalid username or password")
                    .setPositiveButton("OK") { _, _ -> }
                    .create()
                    .show()
            }
        }
    }

    // Validation for Email Address
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    data class User(val username: String, val email: String, val password: String)
}