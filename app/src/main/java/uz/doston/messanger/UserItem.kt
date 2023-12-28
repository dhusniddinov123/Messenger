package uz.doston.messanger

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.doston.messanger.Navigation.Screens

@Composable
fun UserItem(name: String, navController: NavController){ Row(
    Modifier
        .fillMaxWidth()
        .padding(17.dp)
        .clickable {
            navController.navigate(route = Screens.User.getFullRoute(name = name))
        }) {
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "Person Icon",
        Modifier.size(32.dp),
        tint = Color.Black,
    )
    Text(fontSize = 24.sp, text = name, color = Color.Black)
}
    Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color(108, 120, 131))
}