package com.happn.android101.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@Composable
fun getPokemonCardResId(id: Int): Int {
    return LocalContext.current.resources.getIdentifier(
        String.format(Locale.getDefault(), "card_%03d", id),
        "drawable",
        "com.happn.android101"
    )
}