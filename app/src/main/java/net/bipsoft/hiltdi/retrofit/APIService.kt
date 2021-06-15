package net.bipsoft.hiltdi.retrofit

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import net.bipsoft.hiltdi.model.PokemonResponse
import retrofit2.http.GET

interface APIService {

    @GET("pokemon")
    fun getPokemon() : Observable<PokemonResponse>
}