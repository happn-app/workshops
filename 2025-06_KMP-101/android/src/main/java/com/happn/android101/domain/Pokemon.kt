package com.happn.android101.domain

@Deprecated(message = "Replace with KMP version", replaceWith = ReplaceWith("com.happn.kmp101.domain.Pokemon"))
class Pokemon(
    val id: Int,
    val name: String,
    val holo: Boolean,
)