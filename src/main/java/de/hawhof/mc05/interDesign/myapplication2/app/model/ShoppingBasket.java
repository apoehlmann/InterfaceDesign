package de.hawhof.mc05.interDesign.myapplication2.app.model;

import java.io.Serializable;
import java.util.*;

/**
 * Created by alex on 04.12.15.
 */
public class ShoppingBasket implements Serializable{
    public final static String STORAGE_ID = "BASKET";
    public Map<String,BasketItem> shoppingBasket = new HashMap<String,BasketItem>();

    public BasketItem findItem(Detail key){
        return this.shoppingBasket.get(key.getID());
    }

    public void deleteItem(Detail key){
        this.shoppingBasket.remove(key);
    }

    public List<BasketItem> getAllItems(){
        return (List<BasketItem>) shoppingBasket.values();
    }

    public void updateItem(Detail detail,int newSize){
        if(shoppingBasket.containsValue(detail)){
            this.shoppingBasket.get(detail.getID()).setcount(newSize);
        }else{
            this.addItem(detail,newSize);
        }

    }

    private void addItem(Detail detail,int size){
        this.shoppingBasket.put(detail.getID(),new BasketItem(detail,size));
    }

    public void setFav(Detail detail,boolean isFav){
        if(shoppingBasket.containsValue(detail)){
            this.shoppingBasket.get(detail.getID()).setIsFav(isFav);
        }else{
            this.addItem(detail,0);
            if(shoppingBasket.containsValue(detail))
                this.shoppingBasket.get(detail.getID()).setIsFav(isFav);
        }
    }

}
