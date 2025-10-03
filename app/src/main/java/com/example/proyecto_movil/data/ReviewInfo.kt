package com.example.proyecto_movil.data

import com.example.proyecto_movil.data.AlbumUI
import com.example.proyecto_movil.data.UserUI

data class ReviewInfo(
    val profileImage: String,
    val id: String,
    val name: String,
    val username: String,
    val content: String,
    val time: String,
    val likes: Int,
    val comments: Int,
    val userId: String,
    //
    val liked: Boolean = false,
){
    constructor() : this ("","","","","","",0,0,"",false)
}