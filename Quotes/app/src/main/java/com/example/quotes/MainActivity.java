package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    public TextView quoteTextView;
    public ImageView nextBtnImageView;
    public ImageView prevBtnImageView;
    public ImageView favBtnImageView;
    public ImageView shareBtnImageView;
    public ArrayList<Quote> quoteList;
    public Stack<Quote> previousQuotes;
    public int index;
    public boolean isPrevious;
    public boolean isFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quote_textView);
        nextBtnImageView = findViewById(R.id.next_btn);
        prevBtnImageView = findViewById(R.id.prev_btn);
        shareBtnImageView = findViewById(R.id.share_btn);

        //import quotes from resource strings.xml
        Resources res = getResources();
        String[] allQuotes = res.getStringArray(R.array.quotes);
        String[] allAuthors = res.getStringArray(R.array.authors);
        quoteList = new ArrayList<>();
        addToQuoteList(allQuotes, allAuthors);

        previousQuotes = new Stack<>();

        //generate random quote from quotes
        final int quotesLength = quoteList.size();
        index = getRandomQuote(quotesLength-1);
        quoteTextView.setText(quoteList.get(index).toString());
        //generate random quote when next button pressed
        nextBtnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPrevious = false;

                index = getRandomQuote(quotesLength-1);
                quoteTextView.setText(quoteList.get(index).toString());
                previousQuotes.push(quoteList.get(index));

            }
        });
        //recall previous quote when back button pressed
        prevBtnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isPrevious  && previousQuotes.size()>0){
                    previousQuotes.pop();
                    isPrevious = true;
                    isFav = true;
                }
                if(previousQuotes.size() > 0 && isPrevious){
                    quoteTextView.setText(previousQuotes.pop().toString());

                } else{
                        Toast.makeText(MainActivity.this, "Get new Quote!", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        //share quote on social media
        shareBtnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, quoteList.get(index).toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        //favorite quote functions

    }

    /**
     * adding all quotes and authors to quoteList arrayList
     * @param allQuotes
     * @param allAuthors
     */
    public void addToQuoteList(String[] allQuotes, String[] allAuthors){
        for(int i = 0; i < allQuotes.length; i++){
            String quote = allQuotes[i];
            String author = allAuthors[i];
            Quote newQuote = new Quote(quote, author);
            quoteList.add(newQuote);
        }
    }

    /**
     * generate random number for quotes between 0 - length-1
     * @param length
     * @return
     */
    public int getRandomQuote(int length){
        return (int) (Math.random() * length) + 1;
    }
}
