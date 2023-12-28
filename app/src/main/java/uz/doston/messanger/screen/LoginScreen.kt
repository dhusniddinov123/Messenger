package uz.doston.messanger.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.doston.messanger.Database.AppDataBase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    var gradient  = Brush.verticalGradient(
        0.0f to Color(0xFF392467),
        0.25f to Color(0xFF5D3587),
        0.75f to Color(0xFFA367B1),
        1.0f to Color(0xFFFFD1E3),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Sign In",
            color = Color.White,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp),
            value = username,
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White),
            onValueChange = {
                username = it
            },
            label = { Text(text = "Your Username", color = Color.White) },
            placeholder = { Text(text = "Username", color = Color.White) },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White),
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(text = "Your Password", color = Color.White) },
            placeholder = { Text(text = "Password", color = Color.White) },
        )
        Button(modifier = Modifier.padding(top = 27.dp), onClick = {
            AppDataBase.getUser(username, password) { result ->
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                if (result == "Successful Login") {
                    AppDataBase.saveUser(context, username.text)
                    navController.navigate("home_screen")
                }
            }
        }) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = "Submit",
                fontSize = 19.sp
            )
        }
        Text(text = "Registration",
        fontSize = 20.sp,
        color = Color.White,
        modifier=Modifier.padding(30.dp).clickable {
            navController.navigate("regist_screen")
        })
    }
}
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun test() {
//    val navController = rememberNavController()
//    NavGraph(navController = navController)
//    LoginScreen(navController = navController)
//}