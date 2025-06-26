package com.happn.android101.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happn.android101.domain.Pokemon
import com.happn.android101.presentation.theme.Android101Theme

@Composable
fun MainScreen(
    pokemonCards: List<Pokemon>,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
    ) {
        items(pokemonCards) { card ->
            PokemonCard(
                pokemon = card,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onCardClick(card.id) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android101Theme {
        MainScreen(
            pokemonCards = listOf(Pokemon(name = "Alakazam", id = 1, holo = true)),
            onCardClick = {}
        )
    }
}