
package com.example.supportu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var showRegister by remember { mutableStateOf(false)}

            MaterialTheme {
                if (showRegister) {
                    RegisterScreen(
                        onBackClick = {showRegister = false},
                        onNextClick = {email, username, password ->}
                        //Insert Account creation here
                    )
                }
                else {
                    LoginScreen(onRegisterClick = {showRegister = true})
                }
            }
        }
    }
}