package com.ej.anroid_jetpack_hello.album

import android.app.Application
import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class AlbumViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoUris = mutableStateOf<List<Uri>>(emptyList<Uri>())
    val photoUris : State<List<Uri>> = _photoUris

    fun fetchPhotos(){
        val uris = mutableListOf<Uri>()
        getApplication<Application>().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
        )?.use { cursor ->
            val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while (cursor.moveToNext()){
                val id = cursor.getLong(idIndex)


                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                uris.add(contentUri)

            }

        }
        _photoUris.value = uris
    }


}