package com.test.spaceapp.domain.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Rover(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String
)
