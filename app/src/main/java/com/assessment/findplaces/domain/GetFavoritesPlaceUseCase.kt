package com.assessment.findplaces.domain

import android.location.Location
import androidx.lifecycle.LiveData
import com.assessment.findplaces.data.PlacesRepository
import com.assessment.findplaces.data.database.PlaceDatabase
import com.assessment.findplaces.data.database.entities.EntityPlace
import com.assessment.findplaces.domain.model.PlaceModel

class GetFavoritesPlaceUseCase(private val database: PlaceDatabase) {

    private val repository = PlacesRepository(database)
    val favorites = repository.places

    suspend operator fun invoke(): LiveData<List<EntityPlace>> {
        return repository.getFavoritesPlaces()
    }

}