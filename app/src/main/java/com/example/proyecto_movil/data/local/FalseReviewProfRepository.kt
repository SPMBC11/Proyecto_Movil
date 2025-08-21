package com.example.proyecto_movil.data.local

import com.example.proyecto_movil.R

object FalseReviewProfRepository {
    val reviews = listOf(
        ReviewProfInfo(
            imageId = R.drawable.gnx,
            title = "GNX",
            artist = "KENDRICK LAMAR",
            score = "85%"
        ),
        ReviewProfInfo(
            imageId = R.drawable.fijacion,
            title = "FIJACIÓN ORAL",
            artist = "SHAKIRA",
            score = "97%"
        ),
        ReviewProfInfo(
            imageId = R.drawable.mullholand,
            title = "MULLHOLLAND DR. OST",
            artist = "ANGELO BADALAMENTI",
            score = "10%",
            isLowScore = true
        )
    )
}