package com.assessment.findplaces.domain

import android.location.Location
import com.assessment.findplaces.data.PlacesRepository
import com.assessment.findplaces.data.database.PlaceDatabase
import com.assessment.findplaces.domain.model.PlaceModel

class GetPlaceUseCase(private val database: PlaceDatabase) {

    private val repository = PlacesRepository(database)

    suspend operator fun invoke(location: Location, keyword: String, radius: Int): List<PlaceModel>{
        val userDefinedLocation = "${location.latitude},${location.longitude}"
        return repository.getNearByPlacesFromApi(userDefinedLocation, keyword, radius)
    }

}