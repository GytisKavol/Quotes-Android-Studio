package com.example.quotes;

public class Quote {
    private String quote;
    private String author;



    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }



    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return quote + "\n\n --" + author;
    }
}
