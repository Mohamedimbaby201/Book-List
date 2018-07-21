package com.example.imbo.booklistingapp;

public class Books {
    String Description_TAG ;
    String TITLE_TAG ;
    String Image_TAG ;
    String Preview_TAG ;


    public Books() {
    }

    public Books(String description_TAG, String TITLE_TAG, String image_TAG, String preview_TAG, String authors_TAG) {
        Description_TAG = description_TAG;
        this.TITLE_TAG = TITLE_TAG;
        Image_TAG = image_TAG;
        Preview_TAG = preview_TAG;
        Authors_TAG = authors_TAG;
    }

    public String getDescription_TAG() {
        return Description_TAG;
    }

    public void setDescription_TAG(String description_TAG) {
        Description_TAG = description_TAG;
    }

    public String getTITLE_TAG() {
        return TITLE_TAG;
    }

    public void setTITLE_TAG(String TITLE_TAG) {
        this.TITLE_TAG = TITLE_TAG;
    }

    public String getImage_TAG() {
        return Image_TAG;
    }

    public void setImage_TAG(String image_TAG) {
        Image_TAG = image_TAG;
    }

    public String getPreview_TAG() {
        return Preview_TAG;
    }

    public void setPreview_TAG(String preview_TAG) {
        Preview_TAG = preview_TAG;
    }

    public String getAuthors_TAG() {
        return Authors_TAG;
    }

    public void setAuthors_TAG(String authors_TAG) {
        Authors_TAG = authors_TAG;
    }

    String Authors_TAG;
}
