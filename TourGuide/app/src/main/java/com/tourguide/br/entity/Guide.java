package com.tourguide.br.entity;

/**
 * Created by gilsonjuniorpro on 4/25/17.
 */

public class Guide {

    private String title;
    private String category;
    private String duration;
    private String contact;
    private int image;

    public Guide(String title, String category, String duration, String contact, int image) {
        this.title = title;
        this.category = category;
        this.duration = duration;
        this.contact = contact;
        this.image = image;
    }

    public Guide(String title, String category, String duration, String contact) {
        this.title = title;
        this.category = category;
        this.duration = duration;
        this.contact = contact;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
