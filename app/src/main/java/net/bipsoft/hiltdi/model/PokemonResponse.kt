package net.bipsoft.hiltdi.model

data class PokemonResponse(
    val count : Int,
    val next : String,
    val previous : String,
    val results : MutableList<Pokemon>
)
