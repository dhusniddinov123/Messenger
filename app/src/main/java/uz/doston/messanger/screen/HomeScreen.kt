package uz.doston.messanger.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var users by remember { mutableStateOf<List<String>>(emptyList()) }

    AppDataBase.getUsers { list ->
        users = list
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Doston kot")
            }
        })
    }) { innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            items(users) {
                UserItem(it)
            }
        }
    }
}

@Composable
fun UserItem(name : String){
    Column {
        Text(text =name )
    }
}