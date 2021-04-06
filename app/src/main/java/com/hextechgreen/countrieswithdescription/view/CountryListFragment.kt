package com.hextechgreen.countrieswithdescription.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hextechgreen.countrieswithdescription.R
import com.hextechgreen.countrieswithdescription.adapter.CountryAdapter
import com.hextechgreen.countrieswithdescription.viewmodel.CountryListViewModel
import kotlinx.android.synthetic.main.fragment_country_list.*

class CountryListFragment : Fragment() {

    private lateinit var viewModel : CountryListViewModel
    private val adapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java)
        viewModel.refreshData()

        view.findViewById<RecyclerView>(R.id.countryListRV).layoutManager = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.countryListRV).adapter = adapter


        observeLiveData()

        /*view.findViewById<Button>(R.id.goToDetailButton).setOnClickListener {

            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(3)
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer {

            it?.let {
                adapter.updateCountryList(it)

                view?.findViewById<RecyclerView>(R.id.countryListRV)?.visibility = View.VISIBLE
                view?.findViewById<TextView>(R.id.countryErrorTextView)?.visibility = View.GONE
                view?.findViewById<ProgressBar>(R.id.countryListProgressBar)?.visibility = View.GONE

            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {

            it?.let {
                if (it) {
                    view?.findViewById<RecyclerView>(R.id.countryListRV)?.visibility = View.GONE
                    view?.findViewById<TextView>(R.id.countryErrorTextView)?.visibility = View.VISIBLE
                    view?.findViewById<ProgressBar>(R.id.countryListProgressBar)?.visibility = View.GONE
                }else {
                    view?.findViewById<TextView>(R.id.countryErrorTextView)?.visibility = View.GONE

                }

            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {

            it?.let {
                if (it){
                    view?.findViewById<RecyclerView>(R.id.countryListRV)?.visibility = View.GONE
                    view?.findViewById<TextView>(R.id.countryErrorTextView)?.visibility = View.GONE
                    view?.findViewById<ProgressBar>(R.id.countryListProgressBar)?.visibility = View.VISIBLE
                }else {
                    view?.findViewById<ProgressBar>(R.id.countryListProgressBar)?.visibility = View.GONE
                }

            }

        })
    }


}