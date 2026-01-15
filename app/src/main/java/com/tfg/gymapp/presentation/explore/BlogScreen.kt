package com.tfg.gymapp.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun BlogScreen(navController: NavController, viewModel: BlogViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val articles by viewModel.articles.collectAsState()

    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDFCF8))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color(0xFF1E5631))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ciencia & Blog", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E5631))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (articles.isEmpty()) {
            CircularProgressIndicator(color = Color(0xFF1E5631))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Loading articles...", color = Color.Gray)
        } else {
            articles.forEach { article ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable { uriHandler.openUri(article.url) },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column {
                        AsyncImage(
                            model = article.urlToImage,
                            contentDescription = article.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                article.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color(0xFF1E5631)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                article.description ?: "No description available.",
                                color = Color.DarkGray,
                                fontSize = 14.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
