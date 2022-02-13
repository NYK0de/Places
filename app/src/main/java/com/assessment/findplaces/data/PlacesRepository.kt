package com.assessment.findplaces.data

import com.assessment.findplaces.data.dto.toDomain
import com.assessment.findplaces.data.network.PlaceService
import com.assessment.findplaces.domain.Place

class PlacesRepository() {
    private val apiClient : PlaceService = PlaceService()

    suspend fun getNearByPlacesFromApi(
        location: String,
        keyword: String,
        radius: Int
    ): List<Place> {
        val response = apiClient.getNearByPlaces(location, keyword, radius)
        return response.map { it.toDomain() }
    }

}