package com.assessment.findplaces.data.database.entities

import androidx.room.*

@Entity(tableName = "favorite_place_review")
class EntityReview constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "place_id")
    val placeId: String?,
    @ColumnInfo(name= "author_name")
    val authorName: String?,
    @ColumnInfo(name= "language")
    val language: String?,
    @ColumnInfo(name= "profile_photo_url")
    val profilePhotoURL: String?,
    @ColumnInfo(name= "text")
    val text: String?,
    @ColumnInfo(name= "time")
    val time: Long?,
)
