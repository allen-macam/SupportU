
package com.example.supportu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        setContent {
            var showRegister by remember { mutableStateOf(false) }
            var isRegistering by remember { mutableStateOf(false) }
            var isLoggedIn by remember { mutableStateOf(auth.currentUser != null) }
            var studentUsername by remember { mutableStateOf("Student") }
            var showAcademic by remember { mutableStateOf(false) }
            var showFinancial by remember { mutableStateOf(false) }
            var showCareer by remember { mutableStateOf(false) }
            var showCommunity by remember { mutableStateOf(false) }

            // Tabs: 0 Academic, 1 Career, 2 Home, 3 Financial, 4 Community
            fun goToTab(tab: Int) {
                showAcademic = tab == 0
                showCareer = tab == 1
                showFinancial = tab == 3
                showCommunity = tab == 4
            }

            MaterialTheme {
                if (isLoggedIn) {
                    when {
                        showAcademic -> AcademicScreen(
                            onBack = { showAcademic = false },
                            onNavigate = { goToTab(it) }
                        )

                        showFinancial -> FinancialScreen(
                            onBack = { showFinancial = false },
                            onNavigate = { goToTab(it) }
                        )

                        showCareer -> CareerScreen(
                            onBack = { showCareer = false },
                            onNavigate = { goToTab(it) }
                        )

                        showCommunity -> CommunityScreen(
                            onBack = { showCommunity = false },
                            onNavigate = { goToTab(it) }
                        )

                        else -> HomeScreen(
                            username = studentUsername,
                            onAcademicClick = { showAcademic = true },
                            onFinancialClick = { showFinancial = true },
                            onCareerClick = { showCareer = true },
                            onCommunityClick = { showCommunity = true },
                            onLogout = {
                                auth.signOut()
                                isLoggedIn = false
                                studentUsername = "Student"
                                showRegister = false
                                showAcademic = false
                                showFinancial = false
                                showCareer = false
                                showCommunity = false
                            }
                        )
                    }
                } else if (showRegister) {
                    RegisterScreen(
                        onBackClick = {
                            if (!isRegistering) {
                                showRegister = false
                            }
                        },
                        onNextClick = { email, username, password ->

                            if (!isRegistering) {
                                isRegistering = true

                                auth.createUserWithEmailAndPassword(
                                    email.trim(),
                                    password
                                ).addOnSuccessListener { result ->

                                    val user = result.user

                                    if (user != null) {
                                        val student = hashMapOf(
                                            "email" to email.trim(),
                                            "username" to username.trim(),
                                            "firstName" to "",
                                            "lastName" to "",
                                            "major" to "",
                                            "year" to ""
                                        )

                                        db.collection("students")
                                            .document(user.uid)
                                            .set(student)
                                            .addOnSuccessListener {
                                                isRegistering = false

                                                Toast.makeText(
                                                    this@MainActivity,
                                                    "Account created successfully!",
                                                    Toast.LENGTH_LONG
                                                ).show()

                                                auth.signOut()
                                                showRegister = false
                                            }
                                            .addOnFailureListener { exception ->
                                                isRegistering = false

                                                Toast.makeText(
                                                    this@MainActivity,
                                                    "Profile save failed: ${exception.message}",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                    } else {
                                        isRegistering = false

                                        Toast.makeText(
                                            this@MainActivity,
                                            "Unable to retrieve new account.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }.addOnFailureListener { exception ->
                                    isRegistering = false

                                    Toast.makeText(
                                        this@MainActivity,
                                        "Registration failed: ${exception.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    )
                } else {
                    LoginScreen(
                        onRegisterClick = {
                            showRegister = true
                        },

                        onLoginClick = { email, password ->

                            auth.signInWithEmailAndPassword(email.trim(), password)
                                .addOnSuccessListener { result ->

                                    val user = result.user

                                    if (user != null) {
                                        db.collection("students")
                                            .document(user.uid)
                                            .get()
                                            .addOnSuccessListener { document ->
                                                studentUsername =
                                                    document.getString("username")
                                                        ?.takeIf { it.isNotBlank() }
                                                        ?: "Student"

                                                isLoggedIn = true
                                            }
                                            .addOnFailureListener { exception ->
                                                Toast.makeText(
                                                    this@MainActivity,
                                                    "Unable to load profile: ${exception.message}",
                                                    Toast.LENGTH_LONG
                                                ).show()

                                                studentUsername = "Student"
                                                isLoggedIn = true
                                            }
                                    }
                                    // Dashboard navigation will go here later.
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Unable to sign in. Check your credentials.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                        }
                    )
                }
            }
        }
    }
}