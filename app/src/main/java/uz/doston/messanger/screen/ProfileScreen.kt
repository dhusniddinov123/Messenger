package uz.doston.messanger.screen


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.doston.messanger.Database.AppDataBase
import uz.doston.messanger.Navigation.Screens
import uz.doston.messanger.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    Scaffold(containerColor = Color(255, 255, 255, 255), topBar = {
        TopBar(navController = navController)
    }) { innerPadding ->
        var password by remember { mutableStateOf(TextFieldValue("")) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(innerPadding),
        ) {

            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Change password",
                color = Color.Black
            )
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(text = "New Password", color = Color.Black) },
                placeholder = { Text(text = "New Password", color = Color.Black) },
            )
            Button(modifier = Modifier.padding(top = 10.dp), onClick = {
                AppDataBase.setPassword(AppDataBase.getSavedUser(context), password.text)
                Toast.makeText(context, "Password changed successfully", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Change")
            }
            Button(modifier = Modifier.padding(top = 5.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
                onClick = {
                    AppDataBase.saveUser(context, "")
                    navController.navigate("login_screen")
                }) {
                Text(text = "Log Out")
            }
        }
    }

}

@Composable
fun TopBar(navController: NavController) {    
    val context = LocalContext.current
    
    Column (modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color(0, 176, 255, 255))
        .padding(start = 20.dp)){

        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    navController.navigate("home_screen")
                },
            tint = Color.White)
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Image(
                painter = painterResource(id = R.drawable.profile_user),
                contentDescription ="user",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(AppDataBase.getSavedUser(context),
                modifier = Modifier
                    .padding(start = 10.dp),
                color = Color.White,
                fontSize = 30.sp)

        }
    }
}
//@SuppressLint("NewApi")
//@Preview(showBackground = true)
//@Composable
//fun test() {
//    val navController = rememberNavController()
//    NavGraph(navController = navController)
//    ProfileScreen(navController = navController)
//}