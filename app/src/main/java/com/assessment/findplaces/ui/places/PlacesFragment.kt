package com.assessment.findplaces.ui.places

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.assessment.findplaces.R
import com.assessment.findplaces.databinding.FragmentPlacesBinding
import com.assessment.findplaces.domain.model.PlaceModel
import com.assessment.findplaces.ui.details.PlaceDetailViewModel
import com.assessment.findplaces.ui.utils.viewmodelfactory.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class PlacesFragment : Fragment(), GoogleMap.OnCameraIdleListener, GoogleMap.OnMarkerClickListener {

    private var _binding: FragmentPlacesBinding? = null
    private val binding get() = _binding!!
    private lateinit var placesViewModel: PlacesViewModel

    private lateinit var map : GoogleMap
    private var isMapReady = false
    private lateinit var position : Location

    private val callback = OnMapReadyCallback {googleMap ->
        map = googleMap
        isMapReady = true
        // change to set the user current location
        val startPosition = LatLng(12.1294562,-86.2677097)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPosition, 16f ))

        map.setOnCameraIdleListener(this@PlacesFragment)
        map.setOnMarkerClickListener(this@PlacesFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity = requireNotNull(this.activity)
        placesViewModel = ViewModelProvider(
                this,
                ViewModelFactory(activity.application)
            ).get(PlacesViewModel::class.java)


        _binding = FragmentPlacesBinding.inflate(inflater, container, false)
        binding.loadingBar.visibility = View.INVISIBLE
        position = Location("")

        placesViewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.loadingBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        placesViewModel.nearByPlaces.observe(viewLifecycleOwner) { listPlaces ->
            setMarkersOnMap(listPlaces)
        }

        val searchField  = binding.searchKeywordBox
        val searchPlace = binding.searchPlace
        searchPlace.setOnClickListener {
            if (searchField.text.isEmpty()){
                Toast.makeText(context, R.string.search_message_empty, Toast.LENGTH_LONG).show()
            }
            else {
                position.latitude = 12.1294562
                position.longitude = -86.2677097
                placesViewModel.getPlaces(position, searchField.text.toString(), 1500)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.eachPlaceMap) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setMarkersOnMap( places: List<PlaceModel> ){
        if (places.isEmpty()){
            Toast.makeText(context, R.string.load_place_error, Toast.LENGTH_LONG).show()
        }
        else {
            map.clear()
            places.map { place ->
                map.addMarker(
                    MarkerOptions()
                        .position(LatLng(place.latitude ?: 0.0, place.longitude ?: 0.0))
                        .title(place.name)
                )?.tag = place.placeId
            }
        }
    }

    override fun onCameraIdle() {
        position.latitude = map.cameraPosition.target.latitude
        position.longitude = map.cameraPosition.target.longitude
        placesViewModel.getPlaces(position, binding.searchKeywordBox.text.toString(), 1500)
    }

    override fun onMarkerClick(pointSelected: Marker): Boolean {
        val action = PlacesFragmentDirections.actionNavigationPlacesToPlaceDetailFragment(
            pointSelected.tag.toString(),
            true,
            pointSelected.title.toString()
        )
        findNavController().navigate(action)
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}