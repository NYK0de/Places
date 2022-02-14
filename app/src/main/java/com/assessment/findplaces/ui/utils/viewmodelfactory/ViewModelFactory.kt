package com.assessment.findplaces.ui.utils.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assessment.findplaces.ui.details.PlaceDetailViewModel
import com.assessment.findplaces.ui.favorites.FavoritesViewModel
import com.assessment.findplaces.ui.places.PlacesViewModel

class ViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlacesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlacesViewModel(app) as T
        }
        if (modelClass.isAssignableFrom(PlaceDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlaceDetailViewModel(app) as T
        }
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}