 package com.example.mathgame.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import uz.doston.messanger.Navigation.NavGraph
import uz.doston.messanger.R

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true){
        delay(2000)
        navController.navigate("login_screen")
    }
    Image(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        painter = painterResource(id = R.drawable.img), contentDescription = null,
//        contentScale = ContentScale.Crop
    )
}



@Preview(showBackground = true)
@Composable
fun test() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
    SplashScreen(navController = navController)
}