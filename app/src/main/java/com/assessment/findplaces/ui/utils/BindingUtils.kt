package com.assessment.findplaces.ui.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.assessment.findplaces.BuildConfig
import com.assessment.findplaces.R
import com.assessment.findplaces.domain.model.PlaceDetailModel
import com.assessment.findplaces.domain.model.PlaceModel
import com.assessment.findplaces.domain.model.ReviewModel
import com.squareup.picasso.Picasso

@BindingAdapter("reviewImage")
fun ImageView.setReviewImage(item: ReviewModel){
    Picasso.with(this.context).load(item.profilePhotoURL).placeholder(R.drawable.image_loading_holder).into(this)
}

@BindingAdapter("placeImage")
fun ImageView.setPlaceImage(item: PlaceModel){
    Picasso.with(this.context).load(R.drawable.image_loading_holder).into(this)
}

@BindingAdapter("loadPlaceImage")
fun ImageView.setLoadPlaceImage(placePhotos: List<String>?){
    val photoUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400" +
            "&photoreference=${placePhotos?.get(0)}" +
            "&key=${BuildConfig.MAPS_API_KEY}"

    val uri = Uri.parse(photoUrl)
        Picasso.with(this.context)
            .load(uri)
            .placeholder(R.drawable.image_no_available_holder)
            .resize(1024, 300)
            .centerInside()
            .into(this)

}