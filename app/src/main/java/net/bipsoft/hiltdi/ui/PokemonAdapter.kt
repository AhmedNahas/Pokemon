package net.bipsoft.hiltdi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.bipsoft.fragment.HomeFragmentDirections
import net.bipsoft.hiltdi.databinding.PokemonItemBinding
import net.bipsoft.hiltdi.model.Pokemon
import net.bipsoft.hiltdi.model.PokemonResponse

class PokemonAdapter(var pokemon: PokemonResponse, val context: Context) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemon.results[position])
    }

    override fun getItemCount(): Int {
        return pokemon.results.size
    }


    inner class PokemonViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(pokemon: Pokemon) {
            binding.pokemonName.text = pokemon.name
            val split = pokemon.url.split("/")
            val pokemonNum = split[split.lastIndex -1]
            val url = "https://pokeres.bastionbot.org/images/pokemon/$pokemonNum.png"

            Glide.with(context).asBitmap().load(url).into(binding.pokemonImage)

            binding.container.setOnClickListener{
                it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(pokemon.name,pokemon.url))
            }
        }
    }
//    https://pokeapi.co/api/v2/pokemon/1/
//    https://pokeres.bastionbot.org/images/pokemon/2.png
}