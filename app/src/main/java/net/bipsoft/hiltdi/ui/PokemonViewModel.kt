package net.bipsoft.hiltdi.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import net.bipsoft.hiltdi.model.Pokemon
import net.bipsoft.hiltdi.model.PokemonResponse
import net.bipsoft.hiltdi.repo.PokemonRepository
import javax.inject.Inject

private const val TAG = "PokemonViewModel"

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repo: PokemonRepository) : ViewModel() {

    var pokemonMutableLiveData: MutableLiveData<PokemonResponse> = MutableLiveData()
    val disposable = CompositeDisposable()

    fun getPokemon(): Observable<PokemonResponse> {
        return repo.getPokemon()
    }

    fun getPokemonsLiveData(): MutableLiveData<PokemonResponse> {
        return pokemonMutableLiveData
    }

    init {

        getPokemon().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PokemonResponse> {
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onNext(pokemon: PokemonResponse) {
                    pokemonMutableLiveData.value = pokemon
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: $e")
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }
            })

    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}