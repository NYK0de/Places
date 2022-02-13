package com.assessment.findplaces.data.network

import com.assessment.findplaces.data.dto.PlaceResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit adapts this Interface to HTTP calls
 */
interface PlaceApiClient {

    /**
     * to load all places according to the location, keyword and radius
     */
    @GET("nearbysearch/json")
    suspend fun getNearByPlaces(
        @Query("location") location: String,
        @Query("keyword") keyword: String,
        @Query("radius") radius: Int
    ) : Response<PlaceResponseDTO>


}