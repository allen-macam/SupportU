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

private val CareerBackground = Color(0xFF0F0D13)
private val CareerCard = Color(0xFF292430)
private val CareerPurple = Color(0xFFB784ED)
private val CareerMuted = Color(0xFFBEB8C8)

@Composable
fun CareerScreen(
    onBack: () -> Unit = {},
    onNavigate: (Int) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }

    // Phone back button / gesture returns to the home screen
    BackHandler(onBack = onBack)

    Scaffold(
        containerColor = CareerBackground,
        bottomBar = { CareerBottomBar(onNavigate = onNavigate) }
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
                        Icons.Default.Work,
                        contentDescription = null,
                        tint = CareerPurple,
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
                            color = CareerMuted,
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
                        "Career Development",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Start building your career while you're still in school.",
                        color = CareerMuted,
                        fontSize = 14.sp
                    )
                }
            }

            // Search box
            item {
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = {
                        Text(
                            "Search internships and jobs",
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
                                contentDescription = "Search"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = CareerCard,
                        unfocusedContainerColor = CareerCard,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            // Main tiles
            item {
                Column {
                    Text(
                        "Explore Opportunities",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Matched to your major and goals.",
                        color = CareerMuted,
                        fontSize = 13.sp
                    )

                    Spacer(Modifier.height(14.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.weight(1f)) {
                            CareerTile(
                                "Internships",
                                "Gain experience related to your major",
                                Icons.Default.Work,
                                Color(0xFF9CA9FF)
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            CareerTile(
                                "Campus Jobs",
                                "On- and off-campus jobs that fit your schedule",
                                Icons.Default.Business,
                                CareerPurple
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.weight(1f)) {
                            CareerTile(
                                "Talk to Recruiters",
                                "Learn what different career fields expect",
                                Icons.Default.Forum,
                                Color(0xFFE48CE5)
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            CareerTile(
                                "Workshops & Fairs",
                                "Career workshops, fairs, and networking events",
                                Icons.Default.Event,
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
                        item { CareerResourceCard("Career Center", Icons.Default.AccountBalance) }
                        item { CareerResourceCard("Resume Help", Icons.Default.Description) }
                        item { CareerResourceCard("Job Fairs", Icons.Default.Event) }
                        item { CareerResourceCard("Alumni", Icons.Default.Groups) }
                    }
                }
            }

            // Placeholder for saved opportunities
            item {
                Column {
                    Text(
                        "Saved Opportunities",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = CareerCard
                        ),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(18.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Work,
                                contentDescription = null,
                                tint = CareerPurple,
                                modifier = Modifier.size(36.dp)
                            )

                            Spacer(Modifier.width(16.dp))

                            Column {
                                Text(
                                    "Nothing saved yet",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Browse internships and jobs matched to your major and save them here.",
                                    color = CareerMuted,
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
private fun CareerTile(
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
            containerColor = CareerCard
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
                color = CareerMuted,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun CareerResourceCard(
    title: String,
    icon: ImageVector
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = CareerCard
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
                tint = CareerPurple,
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
private fun CareerBottomBar(onNavigate: (Int) -> Unit) {
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
        containerColor = CareerCard,
        tonalElevation = 0.dp
    ) {
        icons.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = index == 1, // Career tab is active on this screen
                onClick = { onNavigate(index) },
                icon = {
                    Icon(icon, contentDescription = labels[index])
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = Color(0xFF70538E),
                    unselectedIconColor = CareerMuted
                )
            )
        }
    }
}