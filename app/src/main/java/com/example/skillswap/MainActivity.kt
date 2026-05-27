package com.example.skillswap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.screens.AvailabilityCalendarScreen
import com.example.skillswap.screens.BookingCheckoutScreen
import com.example.skillswap.screens.BookingConfirmationScreen
import com.example.skillswap.screens.ChatScreen
import com.example.skillswap.screens.CreateListingScreen
import com.example.skillswap.screens.EarningsScreen
import com.example.skillswap.screens.FeedbackScreen
import com.example.skillswap.screens.HomeScreen
import com.example.skillswap.screens.LeaveReviewScreen
import com.example.skillswap.screens.LoginScreen
import com.example.skillswap.screens.MessageScreen
import com.example.skillswap.screens.MyBookingScreen
import com.example.skillswap.screens.OnboardingScreenTwo
import com.example.skillswap.screens.PaymentGatewayScreen
import com.example.skillswap.screens.PortfolioGalleryScreen
import com.example.skillswap.screens.ProfileScreen
import com.example.skillswap.screens.ProviderProfileScreen
import com.example.skillswap.screens.RatingsReviewsScreen
import com.example.skillswap.screens.RewardsScreen
import com.example.skillswap.screens.SearchScreen
import com.example.skillswap.screens.SettingsScreen
import com.example.skillswap.screens.SignupScreen
import com.example.skillswap.screens.SplashOnboardingScreen
import com.example.skillswap.screens.SwapScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // 1. Set up the Navigation Controller
                    val navController = rememberNavController()

                    // 2. Define the Navigation Map
                    NavHost(navController = navController, startDestination = "splash") {

                        // Screen 1: The Splash Screen
                        composable("splash") {
                            SplashOnboardingScreen(
                                onScreenTap = {
                                    // This triggers when the screen is tapped!
                                    navController.navigate("onboarding_two")
                                }
                            )
                        }

                        // Screen 2: The Avatar Screen
                        composable("onboarding_two") {
                            OnboardingScreenTwo(
                                onJoinNowClick = {
                                    navController.navigate("Signup")
                                },
                                onLoginClick = {
                                    navController.navigate("login")
                                }
                            )
                        }

                        // Screen 3: Signup Screen
                        composable("signup") {
                            SignupScreen(
                                onCreateAccountClick = {
                                    // Later, we will add backend logic here!
                                },
                                onLoginClick = {
                                    navController.navigate("login")
                                }
                            )
                        }

                        // Screen 4: Login Screen
                        composable("login") {
                            LoginScreen(
                                onBackClick = {
                                    // Pops the back stack to go to the previous screen
                                    navController.popBackStack()
                                },
                                onLoginClick = {
                                    navController.navigate("home")
                                },
                                onForgotPasswordClick = {
                                    // Will go to Forgot Password later
                                }
                            )
                        }

                        // Screen 5: Home/Dashboard
                        composable("home") {
                            HomeScreen(
                                onSearchClick = {navController.navigate("search")},
                                navController = navController,
                            )
                        }

                        // Screen 6: Search Screen
                        composable("search") {
                            SearchScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() },
                                onProviderClick = { navController.navigate("provider_profile") }
                            )
                        }

                        // Screen 7: Messages List
                        composable("messages") {
                            MessageScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() },
                                onChatClick = {navController.navigate("chat_thread")}
                            )
                        }

                        // Screen 8: Individual Chat Thread
                        composable("chat_thread") {
                            ChatScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() },
                                onCheckoutClick = { navController.navigate("checkout")}
                            )
                        }

                        // Screen 9: Checkout
                        composable("checkout") {
                            BookingCheckoutScreen(
                                onBackClick = { navController.popBackStack() },
                                onProceedClick = { navController.navigate("payment") } // Moves to payment
                            )
                        }

                        // Screen 10: Payment
                        composable("payment") {
                            PaymentGatewayScreen(
                                onBackClick = { navController.popBackStack() },
                                onPayClick = { navController.navigate("confirmation") } // Moves to success
                            )
                        }

                        // Screen 11: Confirmation
                        composable("confirmation") {
                            BookingConfirmationScreen(
                                onBackToHomeClick = {
                                    // Wipes the stack and takes you back to the home dashboard
                                    navController.navigate("home")
                                }
                            )
                        }

                        // Screen 12: User Profile / Menu
                        composable("profile") {
                            ProfileScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() },
                                onBookingClick = { navController.navigate("my_bookings")},
                                onEarningsClick = { navController.navigate("earnings")},
                                onFeedbackClick = { navController.navigate("feedback")},
                                onSettingsClick = { navController.navigate("settings")},
                                onRewardsClick = { navController.navigate("rewards")}
                            )
                        }

                        // Screen 13: My Bookings
                        composable("my_bookings") {
                            MyBookingScreen(
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        // Screen 14: Earnings Analytics
                        composable("earnings") {
                            EarningsScreen(
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        // Screen 15: Feedback / Reviews
                        composable("feedback") {
                            FeedbackScreen(
                                onBackClick = { navController.popBackStack() },
                                onLeaveReviewClick = { navController.navigate("leave_review")}
                            )
                        }

                        // Screen 16: Leave a Review
                        composable("leave_review") {
                            LeaveReviewScreen(
                                onBackClick = { navController.popBackStack() },
                                onSubmitClick = {
                                    // Navigate back to the feedback list after submitting
                                    navController.popBackStack()
                                }
                            )
                        }

                        // Screen 17: Settings
                        composable("settings") {
                            SettingsScreen(
                                onBackClick = { navController.popBackStack() },
                                onLogoutClick = {
                                    // Typically this would clear user data and return to Login/Splash
                                    navController.navigate("login") {
                                        popUpTo(0) // Clears the entire backstack
                                    }
                                },
                                onSwitchToSellerClick = { navController.navigate("create_listing")}
                            )
                        }

                        // Screen 18: Rewards / Referral
                        composable("rewards") {
                            RewardsScreen(
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        // Screen 19: Provider Profile
                        composable("provider_profile") {
                            ProviderProfileScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() },
                                onNextClick = { navController.navigate("portfolio_gallery") } // Or wherever "Next" should go!
                            )
                        }

                        // Screen 20: Portfolio Gallery
                        composable("portfolio_gallery") {
                            PortfolioGalleryScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() },
                                onReviewsClick = { navController.navigate("ratings_reviews") }
                            )
                        }

                        // Screen 21: Ratings & Reviews
                        composable("ratings_reviews") {
                            RatingsReviewsScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        // Screen 22: Create Listing (Seller Mode)
                        composable("create_listing") {
                            CreateListingScreen(
                                onBackClick = { navController.popBackStack() },
                                onPublishClick = {
                                    // Could navigate to a success screen or back to profile
                                    navController.navigate("availability_calendar")
                                }
                            )
                        }

                        // Screen 23: Availability Calendar
                        composable("availability_calendar") {
                            AvailabilityCalendarScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        // Screen 24: Swap Management
                        composable("swap") {
                            SwapScreen(
                                navController = navController,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}



