package com.assessment.findplaces.domain

import com.assessment.findplaces.data.PlacesRepository
import com.assessment.findplaces.data.database.PlaceDatabase
import com.assessment.findplaces.domain.model.PlaceDetailModel


class SavePlaceAsFavoriteUseCase(private val database: PlaceDatabase) {
    private val repository = PlacesRepository(database)

    suspend operator fun invoke(placeDetailModel: PlaceDetailModel){
        return repository.savePlaceAsFavorite(placeDetailModel)
    }


}