package com.rzsahu.quotenice.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rzsahu.quotenice.model.Quote

class MainViewModel(private val context: Context) : ViewModel() {

    private var quoteList = emptyList<Quote>()
    private var index = 0

    init {
        quoteList = loadQuoteFromAsset()
        index = (quoteList.indices).random()
    }

    private fun loadQuoteFromAsset(): List<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        val listType = object: TypeToken<List<Quote>>() {}.type
        return gson.fromJson(json, listType)
    }

    fun getQuote(): Quote = quoteList[index]

    fun getNextQuote(): Quote = quoteList[getNextIndex()]

    private fun getNextIndex(): Int {
        return (++index + quoteList.size) % quoteList.size
    }

    fun getPrevQuote(): Quote = quoteList[getPrevIndex()]

    private fun getPrevIndex(): Int {
        return (--index + quoteList.size) % quoteList.size
    }
}