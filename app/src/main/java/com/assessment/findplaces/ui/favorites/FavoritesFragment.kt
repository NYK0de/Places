package com.assessment.findplaces.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.findplaces.databinding.FragmentFavoritesBinding
import com.assessment.findplaces.ui.details.PlaceDetailViewModel
import com.assessment.findplaces.ui.details.adapter.FavoritesAdapter
import com.assessment.findplaces.ui.details.adapter.ReviewAdapter
import com.assessment.findplaces.ui.utils.viewmodelfactory.ViewModelFactory

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var favoritesViewModel : FavoritesViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity = requireNotNull(this.activity)
        favoritesViewModel = ViewModelProvider(this,ViewModelFactory(activity.application))
            .get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = favoritesViewModel

        val favoritesAdapter = FavoritesAdapter()
        binding.listFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        favoritesViewModel.getFavoritesPlaces()
        favoritesViewModel.favoritesPlaces.observe(viewLifecycleOwner, Observer { list ->
            favoritesAdapter.submitList(list)
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}