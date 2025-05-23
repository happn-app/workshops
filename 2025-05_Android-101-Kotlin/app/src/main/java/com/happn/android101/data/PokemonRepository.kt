package com.happn.android101.data

import android.content.Context
import com.happn.android101.R
import com.happn.android101.domain.PokemonCard
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class PokemonRepository(private val context: Context) {

    fun getPokemonCards(): List<PokemonCard> {
        val inputStream = context.resources.openRawResource(R.raw.pokemon_base_set)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = reader.readText()
        
        val jsonObject = JSONObject(jsonString)
        val cardsArray = jsonObject.getJSONArray("cards")
        
        val pokemonCards = mutableListOf<PokemonCard>()
        
        for (i in 0 until cardsArray.length()) {
            val card = cardsArray.getJSONObject(i)
            val id = card.getInt("id")
            val name = card.getString("name")
            val holo = card.getBoolean("holo")
            pokemonCards.add(PokemonCard())
        }
        
        return pokemonCards
    }
}
