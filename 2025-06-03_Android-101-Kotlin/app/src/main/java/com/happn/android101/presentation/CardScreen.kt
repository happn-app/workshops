package com.happn.android101.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happn.android101.domain.PokemonCard
import com.happn.android101.presentation.utils.holo

@Composable
fun CardScreen(
    pokemonCard: PokemonCard,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .holo(
                    shape = RoundedCornerShape(percent = 4),
                    enabled = pokemonCard.holo,
                ),
            pokemonCard = pokemonCard,
        )
    }
}