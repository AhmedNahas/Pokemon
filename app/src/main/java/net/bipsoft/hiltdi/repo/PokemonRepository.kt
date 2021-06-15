package net.bipsoft.hiltdi.repo

import io.reactivex.Observable
import net.bipsoft.hiltdi.model.PokemonResponse
import net.bipsoft.hiltdi.retrofit.APIService
import javax.inject.Inject

class PokemonRepository
@Inject
constructor(private val apiService: APIService) {

    fun getPokemon(): Observable<PokemonResponse> {
        return apiService.getPokemon()
    }
}