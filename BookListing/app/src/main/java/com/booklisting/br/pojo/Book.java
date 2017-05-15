package com.booklisting.br.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gilsonjuniorpro on 4/28/17.
 */

public class Book implements Parcelable {

    private String title;
    private String authors;
    private String publisher;
    private String description;
    private String ISBN;
    private String thumbnail;
    private double rating;

    public Book(String title, String authors, String publisher, String description, String ISBN, double rating) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.description = description;
        this.ISBN = ISBN;
        this.rating = rating;
    }

    public Book(String title, String authors, String publisher, String description, String ISBN, double rating, String thumbnail) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.description = description;
        this.ISBN = ISBN;
        this.rating = rating;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.authors);
        dest.writeString(this.publisher);
        dest.writeString(this.description);
        dest.writeString(this.ISBN);
        dest.writeString(this.thumbnail);
        dest.writeDouble(this.rating);
    }

    protected Book(Parcel in) {
        this.title = in.readString();
        this.authors = in.readString();
        this.publisher = in.readString();
        this.description = in.readString();
        this.ISBN = in.readString();
        this.thumbnail = in.readString();
        this.rating = in.readDouble();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
