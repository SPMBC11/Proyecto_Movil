package com.example.proyecto_movil.data.datasource

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class StorageRemoteDataSource  @Inject constructor(
    private val storage :FirebaseStorage
){
    suspend fun uploadImage(path: String, uri: Uri): String {
        val imageRef = storage.reference.child(path)
       imageRef.putFile(uri).await()
        return imageRef.downloadUrl.await().toString()

    }
}