package de.hawhof.mc05.interDesign.myapplication2.app.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by alex on 04.12.15.
 */
public class BasketItem implements Serializable {

    private final Detail detail;
    private int count = 0;
    private boolean isFav = false;

    public static final long serialVersionUID = 12345;

    public BasketItem(Detail detail,int count){
       // BasketItem.serialVersionUID = detail.getID().codePointCount(0,detail.getID().length()-1);
        this.detail = detail;
        this.count = count;
    }

    public boolean getIsFav() {
        return isFav;
    }

    public int getCount() {
        return count;
    }

    public void setIsFav(boolean isFav) {
        this.isFav = isFav;
    }

    public void setcount(int count) {
        this.count = count;
    }

    public Drawable getImage() {
        return this.detail.getImage();
    }

    public CharSequence getTitle() {
        return this.detail.getTitle();
    }

    public float getPrice() {
        return this.detail.getPrice();
    }

    public String getFullPrice() {
        return this.getPrice()*this.getCount() + "€";
    }
}
