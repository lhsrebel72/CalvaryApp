package com.example.calvaryapp.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServicesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Services"
    }

    val text: LiveData<String> = _text
}