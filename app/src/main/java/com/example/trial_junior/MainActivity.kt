package com.example.trial_junior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trial_junior.feature_junior.presentation.invitation_list.BasicInterviewListViewModel
import com.example.trial_junior.feature_junior.presentation.invitation_list.InvitationListScreen
import com.example.trial_junior.feature_junior.presentation.invitation_list.InvitationListViewModel
import com.example.trial_junior.feature_junior.presentation.invitation_list.SpecialInterviewListViewModel
import com.example.trial_junior.feature_junior.presentation.invitation_list.WishListViewModel
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
                        startDestination = Screen.InvitationItemListScreen.route
                    ) {
                        composable(route = Screen.InvitationItemListScreen.route) {
                            InvitationListScreen(
                                navController = navController,
                                invitationViewModel = invitationViewModel,
                                wishlistViewModel = wishlistViewModel,
                                basicInterviewViewModel = basicInterviewViewModel,
                                specialInterviewViewModel = specialInterviewViewModel
                            )
                        }

                        // Placeholder for InvitationNewUpdateScreen route
                        /*
                        composable(route = Screen.InvitationNewUpdateScreen.route) {
                            // TODO: Implement InvitationNewUpdateScreen composable
                        }
                        */
                    }
                }
            }
        }
    }
}



