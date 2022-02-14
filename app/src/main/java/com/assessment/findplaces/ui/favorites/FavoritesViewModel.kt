package com.assessment.findplaces.ui.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.findplaces.data.database.getDatabase
import com.assessment.findplaces.domain.GetFavoritesPlaceUseCase
import com.assessment.findplaces.domain.model.PlaceModel
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private var getFavoritesPlacesUseCase = GetFavoritesPlaceUseCase(database)

    private val _favoritesPlaces = getFavoritesPlacesUseCase.favorites

    val favoritesPlaces : LiveData<List<PlaceModel>> = _favoritesPlaces
    val isLoading = MutableLiveData<Boolean>(false)

    fun getFavoritesPlaces(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFavoritesPlacesUseCase.invoke()
            //_favoritesPlaces.postValue(result)
            isLoading.postValue(false)
        }
    }
}