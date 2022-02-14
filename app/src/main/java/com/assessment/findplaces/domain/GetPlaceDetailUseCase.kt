package com.assessment.findplaces.domain

import com.assessment.findplaces.data.PlacesRepository
import com.assessment.findplaces.data.database.PlaceDatabase
import com.assessment.findplaces.domain.model.PlaceDetailModel

class GetPlaceDetailUseCase(database: PlaceDatabase) {

    private val repository = PlacesRepository(database)

    suspend operator fun invoke(placeId: String, isFromNetwork: Boolean) : PlaceDetailModel? {
        val result = repository.getPlaceDetail(placeId, isFromNetwork)

        return result;
    }

}