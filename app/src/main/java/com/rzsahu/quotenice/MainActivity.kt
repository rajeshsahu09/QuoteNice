package com.rzsahu.quotenice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rzsahu.quotenice.model.Quote
import com.rzsahu.quotenice.viewmodel.MainViewModel
import com.rzsahu.quotenice.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val tag = "QuoteNice.MainActivity"

    private lateinit var mainViewModel: MainViewModel
    private lateinit var quoteText: TextView
    private lateinit var authorText: TextView
    private lateinit var prevText: TextView
    private lateinit var nextText: TextView
    private lateinit var shareButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(Observer())

        createViewModel()
        inflateView()
        setQuote(mainViewModel.getQuote())
    }

    private fun createViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(applicationContext)
        )[MainViewModel::class.java]
    }

    private fun inflateView() {
        quoteText = findViewById(R.id.quote_text)
        authorText = findViewById(R.id.author)
        prevText = findViewById(R.id.previous)
        prevText.setOnClickListener { prevQuote() }
        nextText = findViewById(R.id.next)
        nextText.setOnClickListener { nextQuote() }
        shareButton = findViewById(R.id.button_share)
        shareButton.setOnClickListener { shareQuote() }
    }

    private fun shareQuote() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
        }
        val extraTextMessage = "${mainViewModel.getQuote().text}\n\n" +
                "--${mainViewModel.getQuote().author}".trim()
        intent.putExtra(Intent.EXTRA_TEXT, extraTextMessage)
        startActivity(intent)
    }

    private fun prevQuote() {
        setQuote(mainViewModel.getPrevQuote())
    }

    private fun nextQuote() {
        setQuote(mainViewModel.getNextQuote())
    }

    private fun setQuote(quote : Quote) {
        quoteText.text = quote.text
        authorText.text = quote.author
    }
}