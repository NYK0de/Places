package com.assessment.findplaces.ui.places

import android.app.Application
import android.location.Location
import androidx.lifecycle.*
import com.assessment.findplaces.data.database.getDatabase
import com.assessment.findplaces.domain.GetPlaceUseCase
import com.assessment.findplaces.domain.model.PlaceModel
import com.assessment.findplaces.ui.details.PlaceDetailViewModel
import kotlinx.coroutines.launch

class PlacesViewModel (application: Application) : ViewModel() {
    private val database = getDatabase(application)
    private var getPlacesUseCase = GetPlaceUseCase(database)

    private val _nearByPlaces = MutableLiveData<List<PlaceModel>>()

    val nearByPlaces : LiveData<List<PlaceModel>> = _nearByPlaces
    val isLoading = MutableLiveData<Boolean>(false)

    fun getPlaces(location: Location, keyword: String, radius: Int){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPlacesUseCase.invoke(location, keyword, radius)
            _nearByPlaces.postValue(result)
            isLoading.postValue(false)
        }
    }

}