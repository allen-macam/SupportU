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

private val CommunityBackground = Color(0xFF0F0D13)
private val CommunityCard = Color(0xFF292430)
private val CommunityPurple = Color(0xFFB784ED)
private val CommunityMuted = Color(0xFFBEB8C8)

@Composable
fun CommunityScreen(
    onBack: () -> Unit = {},
    onNavigate: (Int) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }

    // Phone back button / gesture returns to the home screen
    BackHandler(onBack = onBack)

    Scaffold(
        containerColor = CommunityBackground,
        bottomBar = { CommunityBottomBar(onNavigate = onNavigate) }
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
                        Icons.Default.Groups,
                        contentDescription = null,
                        tint = CommunityPurple,
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
                            color = CommunityMuted,
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
                        "Community",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Meet classmates who share your major, classes, and interests.",
                        color = CommunityMuted,
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
                            "Search students, clubs, and groups",
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
                        focusedContainerColor = CommunityCard,
                        unfocusedContainerColor = CommunityCard,
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
                        "Connect",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Build your network and sense of belonging.",
                        color = CommunityMuted,
                        fontSize = 13.sp
                    )

                    Spacer(Modifier.height(14.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.weight(1f)) {
                            CommunityTile(
                                "Find Classmates",
                                "Meet students in your major and classes",
                                Icons.Default.PersonSearch,
                                Color(0xFF81E6B4)
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            CommunityTile(
                                "Study Groups",
                                "Join or start a group for your courses",
                                Icons.Default.Groups,
                                CommunityPurple
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.weight(1f)) {
                            CommunityTile(
                                "Clubs",
                                "Clubs that match your interests and major",
                                Icons.Default.Star,
                                Color(0xFFE48CE5)
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            CommunityTile(
                                "Campus Events",
                                "Mixers, workshops, and meetups",
                                Icons.Default.Event,
                                Color(0xFF9CA9FF)
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
                        item { CommunityResourceCard("Student Life", Icons.Default.School) }
                        item { CommunityResourceCard("Clubs", Icons.Default.Groups) }
                        item { CommunityResourceCard("Events", Icons.Default.Event) }
                        item { CommunityResourceCard("Library", Icons.Default.MenuBook) }
                    }
                }
            }

            // Placeholder for joined groups
            item {
                Column {
                    Text(
                        "My Groups",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = CommunityCard
                        ),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(18.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Groups,
                                contentDescription = null,
                                tint = CommunityPurple,
                                modifier = Modifier.size(36.dp)
                            )

                            Spacer(Modifier.width(16.dp))

                            Column {
                                Text(
                                    "No groups yet",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Find classmates or clubs that match your interests to get started.",
                                    color = CommunityMuted,
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
private fun CommunityTile(
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
            containerColor = CommunityCard
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
                color = CommunityMuted,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun CommunityResourceCard(
    title: String,
    icon: ImageVector
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = CommunityCard
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
                tint = CommunityPurple,
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
private fun CommunityBottomBar(onNavigate: (Int) -> Unit) {
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
        containerColor = CommunityCard,
        tonalElevation = 0.dp
    ) {
        icons.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = index == 4, // Community tab is active on this screen
                onClick = { onNavigate(index) },
                icon = {
                    Icon(icon, contentDescription = labels[index])
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = Color(0xFF70538E),
                    unselectedIconColor = CommunityMuted
                )
            )
        }
    }
}
