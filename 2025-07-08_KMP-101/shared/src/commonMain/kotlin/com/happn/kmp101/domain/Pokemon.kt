package com.happn.kmp101.domain

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
) {
    fun getImageUrl(): String {
        return "https://pokecardex.b-cdn.net/assets/images/sets/MEW/HD/$id.jpg"
    }

    fun getFrontSpriteUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }

    fun getBackSpriteUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"
    }
}
