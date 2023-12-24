package uz.doston.messanger.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.doston.messanger.screen.HomeScreen
import uz.doston.messanger.screen.LoginScreen
import uz.doston.messanger.screen.MessageScreen
import uz.doston.messanger.screen.ProfileScreen
import uz.doston.messanger.screen.RegistScreen

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route)
    {
        composable(route = Screens.Home.route){
            HomeScreen()
        }
        composable(route = Screens.Login.route){
            LoginScreen(navController)
        }
        composable(route = Screens.Message.route){
            MessageScreen()
        }
        composable(route = Screens.Profile.route){
            ProfileScreen()
        }
        composable(route = Screens.Regist.route){
            RegistScreen(navController)
        }


    }
}