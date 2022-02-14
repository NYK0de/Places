package com.assessment.findplaces.data.dto

import com.assessment.findplaces.domain.model.PlaceDetailModel
import com.assessment.findplaces.domain.model.ReviewModel
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import java.lang.ref.PhantomReference

data class PlaceDetailResponseDTO (
    @SerializedName("result")
    val placeDetail: PlaceDetail?,
    val status: String
)

data class PlaceDetail (
    @SerializedName("place_id")
    val placeId : String,
    @SerializedName("formatted_address")
    val formattedAddress: String,
    @SerializedName("formatted_phone_number")
    val formattedPhoneNumber: String?,
    val geometry: Geometry,
    val name: String,
    val photos: List<Photo>?,
    val rating: Double?,
    val reviews: List<Review>?,
    @SerializedName("user_ratings_total")
    val userRatingsTotal: Long
)

fun PlaceDetail.toDomain() = PlaceDetailModel(
    placeId = placeId,
    placeName = name,
    placeAddress = formattedAddress,
    placePhotos = photos?.map { it ->
        it.photoReference
    },
    rating = rating?.toFloat(),
    reviews = reviews?.map { it ->
        ReviewModel(
            authorName = it.authorName,
            profilePhotoURL = it.profilePhotoURL,
            text = it.text,
            time = it.time,
            language = it.language
        )
    },
    geometry = LatLng(geometry.location.lat, geometry.location.lng)
)

data class Photo (
    val height: Long,
    //@SerializedName("html_attributions")
    //val htmlAttributions: List<String>,
    @SerializedName("photo_reference")
    val photoReference: String,
    val width: Long
)

data class Review (
    @SerializedName("author_name")
    val authorName: String?,
    @SerializedName("author_url")
    val authorURL: String,
    val language: String?,
    @SerializedName("profile_photo_url")
    val profilePhotoURL: String?,
    val rating: Long?,
    @SerializedName("relative_time_description")
    val relativeTimeDescription: String?,
    val text: String?,
    val time: Long?
)