package com.assessment.findplaces.ui.details

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.assessment.findplaces.data.database.getDatabase
import com.assessment.findplaces.domain.GetPlaceDetailUseCase
import com.assessment.findplaces.domain.SavePlaceAsFavoriteUseCase
import com.assessment.findplaces.domain.model.PlaceDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceDetailViewModel(application: Application) : ViewModel() {
    private val database = getDatabase(application)
    var getPlaceDetailUseCase = GetPlaceDetailUseCase(database)
    var savePlaceAsFavoriteUseCase = SavePlaceAsFavoriteUseCase(database)


    private val _place = MutableLiveData<PlaceDetailModel>().apply {
        //value = PlaceDetailModel(null, "")
    }

    val place : LiveData<PlaceDetailModel> = _place;
    private val _isSaving = MutableLiveData(false)
    val isSaving : LiveData<Boolean> = _isSaving

    fun getPlaceDetail(placeId: String, isFromNetwork: Boolean){
        viewModelScope.launch {
            _isSaving.postValue(true)
            val result = getPlaceDetailUseCase(placeId, isFromNetwork)
            _place.postValue(result)
            _isSaving.postValue(false)
        }
    }

    fun saveAsFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            _isSaving.postValue(true)
            place.value?.let { savePlaceAsFavoriteUseCase(it) }
            _isSaving.postValue(false)
        }
    }

}
