package com.assessment.findplaces.domain

import android.location.Location
import com.assessment.findplaces.data.PlacesRepository

class GetPlaceUseCase() {

    private val repository = PlacesRepository()

    suspend operator fun invoke(location: Location, keyword: String, radius: Int): List<Place>{
        val userDefinedLocation = "${location.latitude},${location.longitude}"
        return repository.getNearByPlacesFromApi(userDefinedLocation, keyword, radius)
    }

}