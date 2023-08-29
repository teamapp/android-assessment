package com.example.myapplication.ui.article

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.LinkedList

class ArticleViewModel : ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    var text: LiveData<String> = _text
}