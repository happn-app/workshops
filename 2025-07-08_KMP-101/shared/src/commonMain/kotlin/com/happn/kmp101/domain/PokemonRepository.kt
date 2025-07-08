package com.happn.kmp101.domain

interface PokemonRepository {
    suspend fun getAll(): List<Pokemon>
}