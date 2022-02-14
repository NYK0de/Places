package com.assessment.findplaces.data.network

import com.assessment.findplaces.data.dto.PlaceDetailResponseDTO
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

    @GET("details/json?fields=place_id,name,formatted_phone_number,formatted_address,photos,rating,reviews,user_ratings_total,geometry")
    suspend fun getPlaceDetail(
        @Query("place_id") placeId: String,
    ) : Response<PlaceDetailResponseDTO>


}