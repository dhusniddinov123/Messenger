package uz.doston.messanger.Navigation

sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Login: Screens("login_screen")
    object Message: Screens("message_screen")
    object Profile: Screens("profile_screen")
    object Regist : Screens("regist_screen")
}