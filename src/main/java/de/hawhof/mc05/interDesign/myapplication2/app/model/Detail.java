package de.hawhof.mc05.interDesign.myapplication2.app.model;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by alex on 03.12.15.
 */
public class Detail implements Serializable{
    private final String id;
    private final String title;
    private final Drawable image;
    private final String kurzBe;
    private final String beschrei;
    private final float price;
    private final String einheit;

    public Detail(TypedArray selectedItem) {
        this.id = selectedItem.getString(0);
        this.title = selectedItem.getString(1);
        this.image = selectedItem.getDrawable(2);
        this.kurzBe = selectedItem.getString(3);
        this.beschrei = selectedItem.getString(4);
        this.price = selectedItem.getFloat(5,0f);
        this.einheit = selectedItem.getString(6);
    }

    public String getTitle() {
        return title;
    }

    public String getPriceString() {
        final String str = ""+this.price +"€";
        if(str == null)
            return "--";
        return str;
    }

    public String getShortDes() {
        return this.kurzBe;
    }

    public String getDes() {
        return this.beschrei;
    }

    public String getID() {
        return this.id;
    }

    public String getType() {
        return this.einheit;
    }

    public Drawable getImage() {
        return this.image;
    }

    public float getPrice() {
        return price;
    }
}