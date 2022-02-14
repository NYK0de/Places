package com.assessment.findplaces.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.assessment.findplaces.data.database.PlaceDatabase
import com.assessment.findplaces.data.database.entities.EntityPlace
import com.assessment.findplaces.data.database.entities.EntityReview
import com.assessment.findplaces.data.database.entities.asDomainModel
import com.assessment.findplaces.data.dto.toDomain
import com.assessment.findplaces.data.network.PlaceService
import com.assessment.findplaces.domain.model.PlaceDetailModel
import com.assessment.findplaces.domain.model.PlaceModel
import com.assessment.findplaces.domain.model.toDatabaseEntity
import com.assessment.findplaces.domain.model.toDatabasePhotos

class PlacesRepository(private val database: PlaceDatabase) {
    private val apiClient : PlaceService = PlaceService()

    val places: LiveData<List<PlaceModel>> =
        Transformations.map(database.placeDao.getAllFavorites()){
            it.asDomainModel()
        }

    suspend fun getNearByPlacesFromApi(
        location: String,
        keyword: String,
        radius: Int
    ): List<PlaceModel> {
        val response = apiClient.getNearByPlaces(location, keyword, radius)
        return response.map { it.toDomain() }
    }

    suspend fun getPlaceDetail(placeId: String, isFromNetwork: Boolean ) : PlaceDetailModel? {
        val response: PlaceDetailModel?
        if (isFromNetwork){
            response = apiClient.getPlaceDetail(placeId).placeDetail?.toDomain()
        }
        else{
            // call to database
            response = apiClient.getPlaceDetail(placeId).placeDetail?.toDomain()
        }
        return response
    }

    suspend fun savePlaceAsFavorite(placeDetailModel: PlaceDetailModel){

        database.placeDao.insertFavoritePlace(placeDetailModel.toDatabaseEntity())

        placeDetailModel.placePhotos?.toDatabasePhotos(placeDetailModel.placeId)
            ?.let { database.placeDao.insertFavoritePhotos(it) }

        placeDetailModel.reviews?.map { reviewModel ->
            reviewModel?.toDatabaseEntity(placeDetailModel.placeId)
        }?.let { database.placeDao.insertFavoriteReview(it as List<EntityReview>) }

        val resRev = database.placeDao.getAllFavoritesReviewsSync()
        resRev.map {
            Log.v("RES-Review", "$it")
        }
        val resPhotos = database.placeDao.getAllFavoritesPhotosSync()
        resRev.map {
            Log.v("RES-Photos", "$it")
        }

    }

    suspend fun getFavoritesPlaces() : LiveData<List<EntityPlace>> {
        return database.placeDao.getAllFavorites()
    }

}