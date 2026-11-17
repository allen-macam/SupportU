package com.example.supportu

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AcademicBackground = Color(0xFF0F0D13)
private val AcademicCard = Color(0xFF292430)
private val AcademicPurple = Color(0xFFB784ED)
private val AcademicMuted = Color(0xFFBEB8C8)

@Composable
fun AcademicScreen(
    onBack: () -> Unit = {}
) {
    var request by remember { mutableStateOf("") }

    // Phone back button / gesture returns to the home screen
    BackHandler(onBack = onBack)

    Scaffold(
        containerColor = AcademicBackground,
        bottomBar = { AcademicBottomBar(onHomeClick = onBack) }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Spacer(Modifier.width(4.dp))

                    Icon(
                        Icons.Default.School,
                        contentDescription = null,
                        tint = AcademicPurple,
                        modifier = Modifier.size(38.dp)
                    )

                    Spacer(Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "SupportU",
                            color = Color.White,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "CSUN Student Hub",
                            color = AcademicMuted,
                            fontSize = 12.sp
                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                }
            }

            // Title
            item {
                Column {
                    Text(
                        "Academic Support",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Tell us what you're struggling with and we'll find the right help.",
                        color = AcademicMuted,
                        fontSize = 14.sp
                    )
                }
            }

            // Request box (future tutor-matching entry point)
            item {
                TextField(
                    value = request,
                    onValueChange = { request = it },
                    placeholder = {
                        Text(
                            "Describe what you need help with",
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Search, null)
                    },
                    trailingIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Submit"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AcademicCard,
                        unfocusedContainerColor = AcademicCard,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            // Main academic tiles
            item {
                Column {
                    Text(
                        "Get Help",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Tutoring and study tools in one place.",
                        color = AcademicMuted,
                        fontSize = 13.sp
                    )

                    Spacer(Modifier.height(14.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.weight(1f)) {
                            AcademicTile(
                                "Find a Tutor",
                                "Get matched with a tutor for your class",
                                Icons.Default.PersonSearch,
                                AcademicPurple
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            AcademicTile(
                                "Study Resources",
                                "Guides and materials for your courses",
                                Icons.Default.MenuBook,
                                Color(0xFF9CA9FF)
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.weight(1f)) {
                            AcademicTile(
                                "Responsible AI Use",
                                "Use AI for coursework the right way",
                                Icons.Default.AutoAwesome,
                                Color(0xFFE48CE5)
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            AcademicTile(
                                "Study Groups",
                                "Find classmates to study with",
                                Icons.Default.Groups,
                                Color(0xFF81E6B4)
                            )
                        }
                    }
                }
            }

            // Campus resources row
            item {
                Column {
                    Text(
                        "CSUN Resources",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(14.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item { AcademicResourceCard("Tutoring", Icons.Default.Groups) }
                        item { AcademicResourceCard("Library", Icons.Default.MenuBook) }
                        item { AcademicResourceCard("Advising", Icons.Default.AccountBalance) }
                        item { AcademicResourceCard("Workshops", Icons.Default.Event) }
                    }
                }
            }

            // Placeholder for upcoming sessions
            item {
                Column {
                    Text(
                        "My Tutoring Sessions",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = AcademicCard
                        ),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(18.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Event,
                                contentDescription = null,
                                tint = AcademicPurple,
                                modifier = Modifier.size(36.dp)
                            )

                            Spacer(Modifier.width(16.dp))

                            Column {
                                Text(
                                    "No sessions yet",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Describe what you need help with to get matched with a tutor.",
                                    color = AcademicMuted,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AcademicTile(
    title: String,
    description: String,
    icon: ImageVector,
    accent: Color,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = AcademicCard
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(30.dp)
            )
            Text(
                title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                description,
                color = AcademicMuted,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun AcademicResourceCard(
    title: String,
    icon: ImageVector
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = AcademicCard
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = AcademicPurple,
                modifier = Modifier.size(27.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                title,
                color = Color.White,
                fontSize = 11.sp,
                lineHeight = 13.sp
            )
        }
    }
}

@Composable
private fun AcademicBottomBar(onHomeClick: () -> Unit) {
    val icons = listOf(
        Icons.Default.School,
        Icons.Default.Work,
        Icons.Default.Home,
        Icons.Default.AccountBalanceWallet,
        Icons.Default.Groups
    )
    val labels = listOf(
        "Academic", "Career", "Home", "Financial", "Community"
    )

    NavigationBar(
        containerColor = AcademicCard,
        tonalElevation = 0.dp
    ) {
        icons.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = index == 0, // Academic tab is active on this screen
                onClick = {
                    if (index == 2) onHomeClick()
                },
                icon = {
                    Icon(icon, contentDescription = labels[index])
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = Color(0xFF70538E),
                    unselectedIconColor = AcademicMuted
                )
            )
        }
    }
}