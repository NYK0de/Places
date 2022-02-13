package com.assessment.findplaces.ui.places

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.findplaces.domain.GetPlaceUseCase
import com.assessment.findplaces.domain.Place
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlacesViewModel : ViewModel() {

    private var getPlacesUseCase = GetPlaceUseCase()

    private val _nearByPlaces = MutableLiveData<List<Place>>()

    val nearByPlaces : LiveData<List<Place>> = _nearByPlaces
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