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
import com.happn.android101.presentation.theme.Android101Theme
import com.happn.kmp101.data.LocalPokemonRepository
import com.happn.kmp101.domain.PokemonRepository
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository: PokemonRepository = LocalPokemonRepository()

        setContent {
            Android101Theme {
                val pokemonCards = remember { runBlocking { repository.getAll() } }

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        MainScreen(
                            pokemonList = pokemonCards,
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
                                    pokemon = card
                                )
                            }
                    }
                }
            }
        }
    }
}
