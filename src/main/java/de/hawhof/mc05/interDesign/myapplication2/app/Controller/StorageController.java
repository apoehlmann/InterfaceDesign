package de.hawhof.mc05.interDesign.myapplication2.app.Controller;

import android.os.Environment;
import de.hawhof.mc05.interDesign.myapplication2.app.model.ShoppingBasket;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by alex on 04.12.15.
 */
public class StorageController {

    public final static File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"shopping.basket");

    public static void save(ShoppingBasket shoppingBasket){
    }

    public static ShoppingBasket load(){
        return null;
    }
}
