package com.hextechgreen.countrieswithdescription.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hextechgreen.countrieswithdescription.model.Country
import com.hextechgreen.countrieswithdescription.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountryListViewModel : ViewModel() {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()

    var countries = MutableLiveData<List<Country>>()
    var countryError = MutableLiveData<Boolean>()
    var countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        /*val country = Country("Turkey","Asia","Ankara","TL","Turkish","www.ss.com")
        val country2 = Country("Germany","Europe","Berlin","EUR","German","www.ss.com")
        val country3 = Country("France","Europe","Paris","EUR","French","www.ss.com")

        val countryList = arrayListOf<Country>(country,country2,country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false*/

        getDataFromAPI()
    }

    private fun getDataFromAPI() {

        countryLoading.value = true

        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        countries.value = t
                        countryLoading.value = false
                        countryError.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                    }

                })
        )

    }

}