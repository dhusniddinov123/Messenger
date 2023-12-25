package uz.doston.messanger.Navigation

const val NAME_KEY = "name_key"
sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Login: Screens("login_screen")
    object Message: Screens("message_screen")
    object Profile: Screens("profile_screen")
    object Regist : Screens("regist_screen")
}