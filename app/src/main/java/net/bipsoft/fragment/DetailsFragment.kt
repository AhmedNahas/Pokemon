package net.bipsoft.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import net.bipsoft.hiltdi.R
import net.bipsoft.hiltdi.databinding.FragmentBlankBinding


class DetailsFragment : Fragment(R.layout.fragment_blank) {

    private var _binding : FragmentBlankBinding? = null
    private val  binding get() = _binding!!
    private val args : DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBlankBinding.bind(view)
        binding.pokemonNameFrag.text  = args.name
        Glide.with(requireContext()).asBitmap().load(args.image).into(binding.pokemonImageFrag)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}