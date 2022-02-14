package com.assessment.findplaces.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.findplaces.R
import com.assessment.findplaces.databinding.FragmentPlaceDetailBinding
import com.assessment.findplaces.ui.details.adapter.ReviewAdapter
import com.assessment.findplaces.ui.utils.viewmodelfactory.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


/**
 *
 */
class PlaceDetailFragment : Fragment() {

    private var _binding : FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var placeDetailViewModel: PlaceDetailViewModel


    val args: PlaceDetailFragmentArgs by navArgs()

    private lateinit var map : GoogleMap
    private var isMapReady = false
    private lateinit var position : LatLng
    private val callback = OnMapReadyCallback {googleMap ->
        map = googleMap
        isMapReady = true
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity = requireNotNull(this.activity)
        placeDetailViewModel = ViewModelProvider(
                this,
            ViewModelFactory(activity.application)
            ).get(PlaceDetailViewModel::class.java)

        _binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)

        val isFromNetwork = args.isFromNetwork
        val placeId = args.placeId

        placeDetailViewModel.getPlaceDetail(placeId, isFromNetwork)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = placeDetailViewModel

        val reviewAdapter = ReviewAdapter()

        binding.placeReviews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }


        placeDetailViewModel.place.observe(viewLifecycleOwner, Observer { placeDetail ->
            reviewAdapter.submitList(placeDetail.reviews)
            position = placeDetail.geometry?.let { LatLng(placeDetail.geometry?.latitude, it.longitude) }!!
            if (isMapReady){
                map.addMarker(
                    MarkerOptions()
                        .position(position)
                        .title(placeDetail.placeName)
                )
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16f ))
            }
        })

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        placeDetailViewModel.isSaving.observe(viewLifecycleOwner, Observer {
            if (it){
                Toast.makeText(activity, "Guardando Favorito", Toast.LENGTH_LONG).show()
            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.placeMap) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}