package com.beyzaterzioglu.tasty1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserInfo(
    @PrimaryKey
    var id: String,
    var userEmail: String,
    var userName: String,
    var phoneNumber: String,

)