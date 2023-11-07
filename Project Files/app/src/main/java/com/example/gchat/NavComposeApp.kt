package com.example.gchat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.example.gchat.nav.Action
import com.example.gchat.nav.Destination.AuthenticationOption
import com.example.gchat.nav.Destination.Home
import com.example.gchat.nav.Destination.Login
import com.example.gchat.nav.Destination.Register
import com.example.gchat.ui.theme.GchatTheme
import com.example.gchat.view.AuthenticationView
import com.example.gchat.view.home.HomeView
import com.example.gchat.view.login.LoginView
import com.example.gchat.view.register.RegisterView


@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    GchatTheme {
        NavHost(
            navController = navController,
            startDestination =
            if (FirebaseAuth.getInstance().currentUser != null)
                Home
            else
                AuthenticationOption
        ) {
            composable(AuthenticationOption) {
                AuthenticationView(
                    register = actions.register,
                    login = actions.login
                )
            }
            composable(Register) {
                RegisterView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Login) {
                LoginView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Home) {
                HomeView()
            }
        }
    }
}