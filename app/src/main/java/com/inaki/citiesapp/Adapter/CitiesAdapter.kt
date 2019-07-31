package com.inaki.citiesapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inaki.citiesapp.MapsActivity
import com.inaki.citiesapp.R
import kotlinx.android.synthetic.main.cities_cards.view.*
import org.json.JSONObject

class CitiesAdapter(private val mCtx: Context,
                    private val cities: MutableList<JSONObject>,
                    private val coord: MutableList<JSONObject>):
    RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    private var cityListFiltered: List<JSONObject> = mutableListOf()
    private var coordListFiltered: MutableList<JSONObject> = mutableListOf()

    class CitiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var country = itemView.tv_country!!
        var name = itemView.tv_name!!
        var lon = itemView.lon!!
        var lat = itemView.latitude!!

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val mapIntent = Intent(itemView.context, MapsActivity::class.java)
            mapIntent.putExtra("lat",lat.text)
            mapIntent.putExtra("lon",lon.text)
            mapIntent.putExtra("city",name.text)
            itemView.context.startActivity(mapIntent)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CitiesViewHolder {
        val citiesView = LayoutInflater.from(mCtx).inflate(R.layout.cities_cards,p0,false)
        return CitiesViewHolder(citiesView)
    }

    //call when you want to filter
    fun filterList(text: String) {
        cityListFiltered = cities.filter { it.get("name").toString().toLowerCase().contains(text) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (cityListFiltered.isEmpty()) {
            cities.size
        }else
            cityListFiltered.size
    }

    override fun onBindViewHolder(p0: CitiesViewHolder, p1: Int) {
        cities.sortBy { it.getString("name") }
        if (cityListFiltered.isEmpty()) {
            p0.name.text = cities[p1].getString("name")
            p0.country.text = cities[p1].getString("country")
            p0.lat.text = coord[p1].getString("lat")
            p0.lon.text = coord[p1].getString("lon")
        }else{
            coordListFiltered.add(p1, cityListFiltered[p1].getJSONObject("coord"))
            p0.name.text = cityListFiltered[p1].getString("name")
            p0.country.text = cityListFiltered[p1].getString("country")
            p0.lat.text = coordListFiltered[p1].getString("lat")
            p0.lon.text = coordListFiltered[p1].getString("lon")
        }
    }
}