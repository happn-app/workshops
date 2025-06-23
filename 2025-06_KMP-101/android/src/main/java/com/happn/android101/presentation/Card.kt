package com.happn.android101.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.happn.android101.domain.PokemonCard
import com.happn.android101.presentation.utils.getPokemonCardResId

@Composable
fun Card(
    pokemonCard: PokemonCard,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .clip(RoundedCornerShape(percent = 4))
            .aspectRatio(63 / 88f),
        painter = painterResource(
            getPokemonCardResId(pokemonCard.id)
        ),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}