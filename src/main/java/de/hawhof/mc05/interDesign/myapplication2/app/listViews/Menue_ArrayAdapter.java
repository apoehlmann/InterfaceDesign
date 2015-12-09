package de.hawhof.mc05.interDesign.myapplication2.app.listViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Menue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 24.11.15.
 */
public class Menue_ArrayAdapter<T> extends ArrayAdapter<T> {

    private final Context context;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public Menue_ArrayAdapter(Context context, int resource, @NonNull List<T> objects) {
        super(context, resource,objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        final Menue menue = (Menue) this.getItem(position);
        View rowView= inflater.inflate(R.layout.menue, null, true);
        if(menue != null) {
            ImageView titleImage = (ImageView) rowView.findViewById(R.id.titleImage);
            TextView titleText = (TextView) rowView.findViewById(R.id.titleText);
            titleText.setText(menue.getTitle());

            ImageView subImage1 = (ImageView) rowView.findViewById(R.id.subImage1);
            TextView subText1 = (TextView) rowView.findViewById(R.id.subText1);
            subText1.setText(menue.getSubMenues().get(0).getString(1));
           // subImage1.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            //subImage1.setImageDrawable(menue.getSubMenues().get(0).getDrawable(2));

            ImageView subImage2 = (ImageView) rowView.findViewById(R.id.subImage2);
            TextView subText2 = (TextView) rowView.findViewById(R.id.subText2);
            subText2.setText(menue.getSubMenues().get(1).getString(1));
            //subImage2.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            //subImage2.setImageDrawable(menue.getSubMenues().get(1).getDrawable(2));

            ImageView subImage3 = (ImageView) rowView.findViewById(R.id.subImage3);
            TextView subText3 = (TextView) rowView.findViewById(R.id.subText3);
            subText3.setText(menue.getSubMenues().get(2).getString(1));
            //subImage3.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            //subImage3.setImageDrawable(menue.getSubMenues().get(2).getDrawable(2));
        }
        return rowView;
    }
}
