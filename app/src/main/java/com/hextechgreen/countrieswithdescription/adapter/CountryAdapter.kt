package com.hextechgreen.countrieswithdescription.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hextechgreen.countrieswithdescription.R
import com.hextechgreen.countrieswithdescription.model.Country
import com.hextechgreen.countrieswithdescription.util.downloadFromUrl
import com.hextechgreen.countrieswithdescription.util.placeHolderProgressBar
import com.hextechgreen.countrieswithdescription.view.CountryListFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_row, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.countryName).text =
            countryList[position].countryName
        holder.view.findViewById<TextView>(R.id.countryRegion).text =
            countryList[position].countryRegion
        holder.view.findViewById<ImageView>(R.id.imageView).downloadFromUrl(countryList[position].imageUrl,
            placeHolderProgressBar(holder.view.context))



        holder.view.setOnClickListener {
            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(position,countryList[position].countryName.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}