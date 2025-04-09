package com.example.firstjetpack

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.firstjetpack.ui.login.LoginScreen
import com.example.firstjetpack.ui.signup.HomeScreen
import com.example.firstjetpack.ui.signup.PolicyScreen
import com.example.firstjetpack.ui.signup.PrivacyScreen
import com.example.firstjetpack.ui.signup.SignUpScreen


sealed class Route{
    data class LoginScreen(val name:String="Login"):Route()
    data class SignupScreen(val name:String="Signup"):Route()
    data class PolicyScreen(val name:String="Policy"):Route()
    data class PrivacyScreen(val name:String="Privacy"):Route()
    data class HomeScreen(val name:String="Home"):Route()
}
@Composable
fun MyNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController,
           startDestination ="login_flow" ) {
        navigation(startDestination = Route.LoginScreen().name,route="login_flow",){

        }
        composable(route = Route.LoginScreen().name) {
            LoginScreen(
                onLoginClick = { navHostController.navigate(Route.HomeScreen().name)
                {
                    popUpTo(route="login_flow")
                }},
                onSignupClick = { navHostController.navigateToSingleTop(
                    Route.SignupScreen().name) }
            )
        }

        composable(route = Route.SignupScreen().name) {
            SignUpScreen(onLoginClick = { navHostController.navigate(Route.LoginScreen().name){
                popUpTo("login_flow")
            } },
                onSignupClick = { navHostController.navigate(Route.SignupScreen().name)
                                },
                onPrivacyClick = {
                    navHostController.navigate(Route.PrivacyScreen().name) {
                        launchSingleTop = true
                    }
                },
                onPolicyClick = {
                    navHostController.navigate(Route.PolicyScreen().name) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(route = Route.PolicyScreen().name)
        {
            PolicyScreen {
                navHostController.navigateUp()
            }
        }


//        composable(route = Route.PrivacyScreen().name)
//        { PrivacyScreen { navHostController.navigateUp() }
//        launchStringTop=true}

        composable(route = Route.HomeScreen().name)
        { HomeScreen() }
    }
}
fun NavController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
