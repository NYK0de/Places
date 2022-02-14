package com.assessment.findplaces.data.database.entities

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.assessment.findplaces.domain.model.PlaceModel

@Entity(
    tableName = "favorite_place",
    indices = [Index(value = ["place_id"], unique = true)]
)
class EntityPlace constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "place_id")
    val placeId: String,
    @ColumnInfo(name= "place_name")
    val placeName: String?,
    @ColumnInfo(name= "place_address")
    val placeAddress: String?,
    @ColumnInfo(name= "place_rating")
    val placeRating: Float?,
    @ColumnInfo(name= "place_latitude")
    val placeLatitude: Double?,
    @ColumnInfo(name= "place_longitude")
    val placeLongitude: Double?,

)
fun List<EntityPlace>.asDomainModel(): List<PlaceModel>{
    return map {
        PlaceModel(
            placeId = it.placeId,
            name = it.placeName,
            latitude = it.placeLatitude,
            longitude = it.placeLongitude
        )
    }
}
