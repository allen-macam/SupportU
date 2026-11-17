
package com.example.supportu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val HomeBackground = Color(0xFF0F0D13)
private val HomeCard = Color(0xFF292430)
private val HomePurple = Color(0xFFB784ED)
private val HomeMuted = Color(0xFFBEB8C8)

@Composable
fun HomeScreen(
    username: String = "Student",
    onAcademicClick: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    var search by remember { mutableStateOf("") }

    Scaffold(
        containerColor = HomeBackground,
        bottomBar = { HomeBottomBar(onAcademicClick) }
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
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.School,
                        contentDescription = null,
                        tint = HomePurple,
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
                            color = HomeMuted,
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

                    IconButton(onClick = onLogout) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Sign out",
                            tint = Color.White
                        )
                    }
                }
            }

            item {
                Column {
                    Text(
                        "Welcome, $username! 👋",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "What can we help you with today?",
                        color = HomeMuted,
                        fontSize = 14.sp
                    )
                }
            }

            item {
                TextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = {
                        Text(
                            "What do you need help with?",
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
                        focusedContainerColor = HomeCard,
                        unfocusedContainerColor = HomeCard,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            item {
                Column {
                    SectionTitle(
                        "Get Support",
                        "Explore resources across campus life."
                    )

                    Spacer(Modifier.height(14.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.weight(1f)) {
                            SupportCard(
                                "Academic Support",
                                "Tutoring, advising, and study resources",
                                Icons.Default.School,
                                HomePurple,
                                onClick = onAcademicClick
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            SupportCard(
                                "Financial Support",
                                "Financial aid and scholarships",
                                Icons.Default.AccountBalanceWallet,
                                Color(0xFFE48CE5)
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.weight(1f)) {
                            SupportCard(
                                "Career Development",
                                "Internships, jobs, and workshops",
                                Icons.Default.Work,
                                Color(0xFF9CA9FF)
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            SupportCard(
                                "Community",
                                "Find classmates and study partners",
                                Icons.Default.Groups,
                                Color(0xFF81E6B4)
                            )
                        }
                    }
                }
            }

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
                        item {
                            ResourceCard("Advising", Icons.Default.AccountBalance)
                        }
                        item {
                            ResourceCard("Library", Icons.Default.MenuBook)
                        }
                        item {
                            ResourceCard("Tutoring", Icons.Default.Groups)
                        }
                        item {
                            ResourceCard("Financial Aid", Icons.Default.Description)
                        }
                        item {
                            ResourceCard("Campus Events", Icons.Default.Event)
                        }
                    }
                }
            }

            item {
                Column {
                    Text(
                        "Upcoming Opportunities",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = HomeCard
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
                                tint = HomePurple,
                                modifier = Modifier.size(36.dp)
                            )

                            Spacer(Modifier.width(16.dp))

                            Column {
                                Text(
                                    "Upcoming CSUN Events",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Discover workshops, career fairs, and campus activities.",
                                    color = HomeMuted,
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
private fun SectionTitle(title: String, subtitle: String) {
    Column {
        Text(
            title,
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            subtitle,
            color = HomeMuted,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun SupportCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accent: Color,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = HomeCard
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
                color = HomeMuted,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun ResourceCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = HomeCard
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
                tint = HomePurple,
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
private fun HomeBottomBar(onAcademicClick: () -> Unit = {}) {
    var selectedTab by remember { mutableIntStateOf(2) }

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
        containerColor = HomeCard,
        tonalElevation = 0.dp
    ) {
        icons.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = {
                    selectedTab = index
                    if (index == 0) onAcademicClick()
                },
                icon = {
                    Icon(icon, contentDescription = labels[index])
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = Color(0xFF70538E),
                    unselectedIconColor = HomeMuted
                )
            )
        }
    }
}
