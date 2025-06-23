package com.happn.android101.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@Composable
fun getPokemonCardResId(id: Int): Int {
    return LocalContext.current.resources.getIdentifier(
        String.format("card_%03d", id),
        "drawable",
        "com.happn.android101"
    )
}