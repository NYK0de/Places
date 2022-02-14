package com.assessment.findplaces.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.assessment.findplaces.data.database.entities.EntityPhoto
import com.assessment.findplaces.data.database.entities.EntityPlace
import com.assessment.findplaces.data.database.entities.EntityReview

@Dao
interface PlaceDao {

    @Transaction
    @Query("SELECT * FROM favorite_place")
    fun getAllFavorites() : LiveData< List< EntityPlace > >

    @Query("SELECT * FROM favorite_place")
    fun getAllFavoritesSync() :  List< EntityPlace >

    @Query("SELECT * FROM favorite_place_review")
    fun getAllFavoritesReviewsSync() :  List< EntityReview >

    @Query("SELECT * FROM favorite_place_photo")
    fun getAllFavoritesPhotosSync() :  List< EntityPhoto >

    @Transaction
    @Query("SELECT * FROM favorite_place WHERE place_id = :mPlaceId")
    fun getAFavorite(mPlaceId: String) : LiveData< EntityPlace >

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePlace(place: EntityPlace)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteReview(reviews: List<EntityReview>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePhotos(photos: List<EntityPhoto>)

}