package com.hextechgreen.countrieswithdescription.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hextechgreen.countrieswithdescription.R
import com.hextechgreen.countrieswithdescription.viewmodel.CountryDetailViewModel


class CountryDetailFragment : Fragment() {

    private lateinit var viewModel: CountryDetailViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        viewModel.getDataFromRoom()

        observe()

        arguments?.let {

           var uuid = CountryDetailFragmentArgs.fromBundle(
               it
           ).countryUuid
            Toast.makeText(view.context,"Country UUID : $uuid",Toast.LENGTH_SHORT).show()
        }
    }

    fun observe() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            view?.findViewById<TextView>(R.id.CDNameTv)?.text = it.countryName
            view?.findViewById<TextView>(R.id.CDCapitalTv)?.text = it.countryCapital
            view?.findViewById<TextView>(R.id.CDRegionTv)?.text = it.countryRegion
            view?.findViewById<TextView>(R.id.CDCurrencyTv)?.text = it.countryCurrency
            view?.findViewById<TextView>(R.id.CDLanguageTv)?.text = it.countryLanguage
        })
    }


}