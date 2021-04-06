package com.hextechgreen.countrieswithdescription.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hextechgreen.countrieswithdescription.model.Country

class CountryListViewModel : ViewModel() {
    var countries = MutableLiveData<List<Country>>()
    var countryError = MutableLiveData<Boolean>()
    var countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        val country = Country("Turkey","Asia","Ankara","TL","Turkish","www.ss.com")
        val country2 = Country("Germany","Europe","Berlin","EUR","German","www.ss.com")
        val country3 = Country("France","Europe","Paris","EUR","French","www.ss.com")

        val countryList = arrayListOf<Country>(country,country2,country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

}