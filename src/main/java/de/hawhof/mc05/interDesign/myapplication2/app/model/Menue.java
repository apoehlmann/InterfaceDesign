package de.hawhof.mc05.interDesign.myapplication2.app.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 02.12.15.
 */
public class Menue implements Serializable{
    private final String title;
    private List<TypedArray> listSub = new ArrayList<TypedArray>();

    public Menue(TypedArray typedArray, Context context) {
        this.title = typedArray.getString(1);
        for(int z = 3;z<typedArray.length();z++) {
            final TypedArray array = context.getResources().obtainTypedArray(typedArray.getResourceId(z, -1));
            this.listSub.add(array);
        }
    }

    public String getTitle(){
        return this.title;
    }

    public List<TypedArray> getSubMenues(){
        return this.listSub;
    }
}