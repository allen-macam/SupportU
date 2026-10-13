
package com.example.supportu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFF0F0D13)
    val fieldColor = Color(0xFF36343B)
    val loginColor = Color(0xFFD0C3DF)
    val registerColor = Color(0xFF4B4559)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.27f))

            Text(
                text = "SupportU",
                color = Color.White,
                fontSize = 36.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(0.20f))

            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text("Username", fontSize = 12.sp)
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text("Password", fontSize = 12.sp)
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Forgot your Password?",
                color = Color.LightGray,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable {
                        // Password recovery screen later
                    }
                    .padding(start = 10.dp, top = 4.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        // Login functionality later
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = loginColor,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("➤ Login", fontSize = 12.sp)
                }

                Button(
                    onClick = onRegisterClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = registerColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("✎ Register", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.weight(0.28f))
        }
    }
}
