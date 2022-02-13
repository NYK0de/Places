package com.assessment.findplaces.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.assessment.findplaces.databinding.FragmentFavoritesBinding
import com.assessment.findplaces.databinding.FragmentPlaceDetailBinding
import com.assessment.findplaces.ui.favorites.FavoritesViewModel

/**
 *
 */
class PlaceDetailFragment : Fragment() {

    private var _binding : FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val placeDetailViewModel = ViewModelProvider(this).get(PlaceDetailViewModel::class.java)

        _binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}