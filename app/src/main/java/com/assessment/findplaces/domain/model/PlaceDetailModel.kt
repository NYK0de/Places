package com.assessment.findplaces.domain.model

import com.assessment.findplaces.data.database.entities.EntityPhoto
import com.assessment.findplaces.data.database.entities.EntityPlace
import com.assessment.findplaces.data.database.entities.EntityReview
import com.google.android.gms.maps.model.LatLng

data class PlaceDetailModel (
    val placeId: String?,
    val placeName: String?,
    val placeAddress: String?,
    val placePhotos: List<String>?,
    val rating: Float?,
    val reviews: List<ReviewModel?>?,
    val geometry: LatLng?
)
data class ReviewModel(
    val authorName: String?,
    val language: String?,
    val profilePhotoURL: String?,
    val text: String?,
    val time: Long?
)

fun PlaceDetailModel.toDatabaseEntity() : EntityPlace {
    return EntityPlace(
        id = 0,
        placeId = placeId ?: "",
        placeName = placeName,
        placeAddress = placeAddress,
        placeRating = rating,
        placeLatitude = geometry?.latitude,
        placeLongitude = geometry?.longitude
    )
}

fun ReviewModel.toDatabaseEntity(placeId: String?): EntityReview {
        return EntityReview(
            id = 0,
            placeId = placeId,
            authorName = authorName,
            language = language,
            profilePhotoURL = profilePhotoURL,
            text = text,
            time = time
        )
}

fun List<String>.toDatabasePhotos(placeId: String?): List<EntityPhoto> {
    return map { reference ->
        EntityPhoto(
            id = 0,
            placeId = placeId,
            photoReference = reference
        )
    }
}