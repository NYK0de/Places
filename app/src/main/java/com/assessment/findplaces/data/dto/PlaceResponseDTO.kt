package com.assessment.findplaces.data.dto

import com.assessment.findplaces.domain.Place
import com.google.gson.annotations.SerializedName

/**
 * With POSTMAN I made the requests to the API https://maps.googleapis.com/maps/api/place/nearbysearch/nearbysearch/json?param=xyz
 * I use https://quicktype.io/ to convert the Response to Kotlin classes then I analyzed the response,
 * and I get only the fields I need to create the DTOs with the data we need to work in our App
 */
data class PlaceResponseDTO(
    @SerializedName("results")
    val listOfPlaces : List<PlaceDTO>,
    val status : String
)

data class PlaceDTO(
    @SerializedName("place_id")
    val placeID: String,
    val name: String,
    val geometry: Geometry,
)

fun PlaceDTO.toDomain() = Place(
    placeId = placeID,
    name = name,
    latitude = geometry.location.lat,
    longitude = geometry.location.lng
)

data class Geometry(
    val location : PlaceLocation
)

data class PlaceLocation(
    val lat: Double,
    val lng: Double
)