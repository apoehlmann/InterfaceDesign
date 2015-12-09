package de.hawhof.mc05.interDesign.myapplication2.app.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;
import de.hawhof.mc05.interDesign.myapplication2.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 03.12.15.
 */
public class SubMenue {
    private final String title;
    private final Drawable image;
    private final List<TypedArray> listSub = new ArrayList<TypedArray>();
    
    private final Activity activity;
    private final TypedArray typedArray;

    public SubMenue(TypedArray typedArray,Activity activity) {

        this.title = typedArray.getString(1);
        this.image = typedArray.getDrawable(2);
        this.activity = activity;
        this.typedArray = typedArray;

        TypedArray array = activity.getResources().obtainTypedArray(typedArray.getResourceId(activity.getPreferences(Context.MODE_PRIVATE).getInt("TYPE",3),3));//;
        Log.i(this.getClass().toString(),this.title+"-Länge es Arrays:"+array.length()+"-"+activity.getPreferences(Context.MODE_PRIVATE).getInt("TYPE",3));
        for(int z = 0;z<array.length();z++) {
            Log.d(this.getClass().toString(),""+z);
            final TypedArray Subarray = activity.getResources().obtainTypedArray(array.getResourceId(z, 0));
            this.listSub.add(array);
        }
    }

    public String getTitle() {
        return title;
    }

    public List<TypedArray> getSubMenues(){

        return this.listSub;
    }

}
