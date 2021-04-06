package com.hextechgreen.countrieswithdescription.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hextechgreen.countrieswithdescription.model.Country

class CountryDetailViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country("Turkey","Asia","Ankara","TL","Turkish","www.ss.com")
        countryLiveData.value = country
    }
}