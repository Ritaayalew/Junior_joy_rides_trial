package com.example.trial_junior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trial_junior.feature_junior.presentation.viewModels.BasicInterviewListViewModel
import com.example.trial_junior.feature_junior.presentation.viewModels.InvitationListViewModel
import com.example.trial_junior.feature_junior.presentation.viewModels.SpecialInterviewListViewModel
import com.example.trial_junior.feature_junior.presentation.viewModels.WishListViewModel
import com.example.trial_junior.feature_junior.presentation.screens.ExampleScreen
import com.example.trial_junior.feature_junior.presentation.screens.WishListNewUpdateScreen
import com.example.trial_junior.feature_junior.presentation.util.Screen
import com.example.trial_junior.ui.theme.TrialJuniorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrialJuniorTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val invitationViewModel: InvitationListViewModel = hiltViewModel()
                    val wishlistViewModel: WishListViewModel = hiltViewModel()
                    val basicInterviewViewModel: BasicInterviewListViewModel = hiltViewModel()
                    val specialInterviewViewModel: SpecialInterviewListViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.ExampleScreen.route
                    ) {
                        composable(route = Screen.ExampleScreen.route) {
                            ExampleScreen(
                                navController = navController,
                                invitationViewModel = invitationViewModel,
                                wishlistViewModel = wishlistViewModel,
                                basicInterviewViewModel = basicInterviewViewModel,
                                specialInterviewViewModel = specialInterviewViewModel
                            )
                        }

                        // Placeholder for InvitationNewUpdateScreen route

                        composable(
                            route = Screen.WishListUpdateScreen.route + "?wishListId={wishListId}",
                            arguments = listOf(
                                navArgument(
                                    name = "wishListId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            WishListNewUpdateScreen(
                                navController = navController
                            )
                        }

                    }
                }
            }
        }
    }
}



