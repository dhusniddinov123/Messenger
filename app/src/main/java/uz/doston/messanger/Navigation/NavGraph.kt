package uz.doston.messanger.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mathgame.screen.SplashScreen
import uz.doston.messanger.screen.HomeScreen
import uz.doston.messanger.screen.LoginScreen
import uz.doston.messanger.screen.MessageScreen
import uz.doston.messanger.screen.ProfileScreen
import uz.doston.messanger.screen.RegistScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route)
    {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Screens.Profile.route) {
            ProfileScreen(navController)
        }
        composable(route = Screens.Regist.route) {
            RegistScreen(navController)
        }


        composable(route = Screens.User.route, arguments = listOf(navArgument(NAME_KEY) {
            type = NavType.StringType
        })) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString(NAME_KEY)
            if (name != null) {
                MessageScreen( name = name, navController = navController)
            }


        }
    }}