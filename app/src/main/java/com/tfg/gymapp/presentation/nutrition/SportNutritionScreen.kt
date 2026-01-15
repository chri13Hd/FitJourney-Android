package com.tfg.gymapp.presentation.nutrition

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tfg.gymapp.presentation.data.supplementList

@Composable
fun SportNutritionScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredSupplements = supplementList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAF5))
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            text = "Nutrición Deportiva",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F5933),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar suplemento...") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(filteredSupplements) { supplement ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (supplement.hasSubList) {
                                navController.navigate("sublist/${supplement.name}")
                            } else {
                                val encodedTitle = Uri.encode(supplement.name)
                                val encodedDesc = Uri.encode(supplement.description)
                                navController.navigate("detail/$encodedTitle/${supplement.imageRes}/$encodedDesc")
                            }
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = supplement.imageRes),
                            contentDescription = supplement.name,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(end = 16.dp)
                        )
                        Text(
                            text = supplement.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF333333)
                        )
                    }
                }
            }
        }
    }
}
