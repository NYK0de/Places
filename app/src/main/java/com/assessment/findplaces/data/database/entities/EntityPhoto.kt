package com.assessment.findplaces.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.lang.ref.PhantomReference

@Entity(tableName = "favorite_place_photo")
class EntityPhoto constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "place_id")
    val placeId: String?,

    @ColumnInfo(name = "photo_reference")
    val photoReference: String
)


