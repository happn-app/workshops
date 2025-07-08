package com.happn.android101.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.happn.android101.data.PokemonRepository
import com.happn.android101.presentation.theme.Android101Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = PokemonRepository(context = this)
        
        setContent {
            Android101Theme {
                val pokemonCards = remember { repository.getPokemonCards() }

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        MainScreen(
                            pokemonCards = pokemonCards,
                            onCardClick = {
                                navController.navigate("card/${it}")
                            }
                        )
                    }
                    composable(
                        route = "card/{cardId}",
                        arguments = listOf(navArgument("cardId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        pokemonCards
                            .find {
                                it.id == backStackEntry.arguments?.getInt("cardId")
                            }?.let { card ->
                                CardScreen(
                                    pokemonCard = card
                                )
                            }
                    }
                }
            }
        }
    }
}
