package de.hawhof.mc05.interDesign.myapplication2.app.listViews;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Detail;

import java.io.File;
import java.util.List;

/**
 * Created by alex on 03.12.15.
 */
public class ProductsOverView_ArrayAdapter<T> extends ArrayAdapter<T> {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ProductsOverView_ArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View rowView= inflater.inflate(R.layout.list_item_products, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.name_textview);
        TextView priTitle = (TextView) rowView.findViewById(R.id.price_textview);
        Typeface typeFace=Typeface.createFromFile(new File(Environment.getExternalStorageDirectory().getPath() +"/"+ getContext().getString(R.string.allerbd)));
        txtTitle.setTypeface(typeFace);priTitle.setTypeface(typeFace);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.product_imageview);

        Detail detail = (Detail) this.getItem(position);
        txtTitle.setText(detail.getTitle());
        priTitle.setText(detail.getPriceString());
        imageView.setImageDrawable(detail.getImage());
        return rowView;
    }
}
