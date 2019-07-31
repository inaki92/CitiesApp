package com.inaki.citiesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inaki.citiesapp.Adapter.CitiesAdapter
import com.inaki.citiesapp.Model.ReadingJson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRecycler: RecyclerView
    private lateinit var citiesAdapter: CitiesAdapter
    private lateinit var readJson: ReadingJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_bar.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                citiesAdapter.filterList(p0!!)
                return true
            }

        })
        readJson = ReadingJson(this)

        init()
    }

    private fun init() {
        mRecycler = cities_recycler
        mRecycler.setHasFixedSize(true)
        mRecycler.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        citiesAdapter = CitiesAdapter(this, readJson.getCitiesData(), readJson.getCoordData())
        mRecycler.adapter = citiesAdapter
    }
}
