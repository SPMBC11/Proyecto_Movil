package com.example.proyecto_movil.data

import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.UserUI

data class ReviewInfo(
   val id: String,
    val content: String,
    val score: Int,
    val is_low_score: Boolean,
    val album_id: String,
    val user_id: String,
    val createdAt: String,
    val updatedAt: String,
    //
    val liked: Boolean = false,
){
   constructor(): this("", "", 0, false, "", "", "", "", false)}