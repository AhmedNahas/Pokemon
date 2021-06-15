package net.bipsoft.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.bipsoft.hiltdi.R
import net.bipsoft.hiltdi.databinding.FragmentHomeBinding
import net.bipsoft.hiltdi.ui.PokemonAdapter
import net.bipsoft.hiltdi.ui.PokemonViewModel

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: PokemonViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var adapter: PokemonAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        viewModel.getPokemonsLiveData().observe(requireActivity(), {
            Log.d(TAG, "onViewCreated: ${it.results.size}")
            View.GONE.also { binding.progressBar.visibility = it }
            adapter = PokemonAdapter(it, requireContext())
            binding.rVPokemon.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}