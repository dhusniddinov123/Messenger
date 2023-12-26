 package com.example.mathgame.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import uz.doston.messanger.Database.AppDataBase
import uz.doston.messanger.R

 @Composable
fun SplashScreen(navController: NavController) {
     val context = LocalContext.current
    LaunchedEffect(key1 = true){
        delay(2000)
        if (AppDataBase.getSavedUser(context) == "") navController.navigate("login_screen")
        else navController.navigate("home_screen")
    }
    Image(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        painter = painterResource(id = R.drawable.img), contentDescription = null,
//        contentScale = ContentScale.Crop
    )
}



//@Preview(showBackground = true)
//@Composable
//fun test() {
//    val navController = rememberNavController()
//    NavGraph(navController = navController)
//    SplashScreen(navController = navController)
//}