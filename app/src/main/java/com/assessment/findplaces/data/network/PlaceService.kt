package com.assessment.findplaces.data.network

import android.util.Log
import com.assessment.findplaces.data.dto.PlaceDTO
import com.assessment.findplaces.data.dto.PlaceDetailResponseDTO
import com.assessment.findplaces.data.network.config.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceService() {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getNearByPlaces(location: String, keyword: String, radius: Int): List<PlaceDTO>{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PlaceApiClient::class.java).getNearByPlaces(location, keyword, radius)
            response.body()?.listOfPlaces ?: emptyList()
        }
    }

    suspend fun getPlaceDetail(placeId: String): PlaceDetailResponseDTO{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PlaceApiClient::class.java).getPlaceDetail(placeId)
            Log.v("INSERVICE","${response.body()?.placeDetail}")
            response.body()!!
        }
    }

}