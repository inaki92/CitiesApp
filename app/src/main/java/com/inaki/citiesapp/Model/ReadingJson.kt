package com.inaki.citiesapp.Model

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList

class ReadingJson(private val mCtx: Context) {

    fun getCitiesData(): MutableList<JSONObject> {
        val citiesList: MutableList<JSONObject> = mutableListOf()
        val jsonString = getAssetsJSON(JSON_FILE_WATCHES)
        val mArray = JSONArray(jsonString)
        for (item in 0 until mArray.length()){
            citiesList.add(item,mArray.getJSONObject(item))
        }
        return citiesList
    }

    fun getCoordData(): MutableList<JSONObject> {
        val coordList: MutableList<JSONObject> = mutableListOf()
        val citiesList = getCitiesData()
        for (i in 0 until citiesList.size){
            coordList.add(i,citiesList[i].getJSONObject("coord"))
        }
        return coordList
    }

    private fun getAssetsJSON(fileName: String): String? {
        var json: String? = null
        try {
            val inputStream = mCtx.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer,Charsets.UTF_8)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }

    companion object {
        private const val JSON_FILE_WATCHES = "cities"
        private const val TAG = "MainActivity"
    }
}