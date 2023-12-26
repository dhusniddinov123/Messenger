package uz.doston.messanger.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import uz.doston.messanger.Database.AppDataBase
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.doston.messanger.Navigation.Screens
import uz.doston.messanger.UserItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var users by remember { mutableStateOf<List<String>>(emptyList()) }

    AppDataBase.getUsers { list ->
        users = list
    }

    Scaffold(containerColor = Color(14, 22, 33), topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(
                14, 22, 33
            )
        ), title = {
            IconButton(onClick = { navController.navigate("profile_screen") }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings Icon",
                    tint = Color(108, 120, 131),
                )
            }
        })
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 17.dp)
                .padding(innerPadding)
        ) {
            items(users) { item ->
                UserItem(name = item, navController)
            }
        }
    }
}

