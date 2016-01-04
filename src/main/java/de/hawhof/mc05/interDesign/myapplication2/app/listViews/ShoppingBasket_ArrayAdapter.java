package de.hawhof.mc05.interDesign.myapplication2.app.listViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.model.BasketItem;

import java.util.List;

/**
 * Created by alex on 03.12.15.
 */
public class ShoppingBasket_ArrayAdapter<T> extends ArrayAdapter<T> {

    private final Context context;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ShoppingBasket_ArrayAdapter(Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View rowView= inflater.inflate(R.layout.shoppingbasket_listitem, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item_title_basket);
        EditText editText = (EditText) rowView.findViewById(R.id.baskettextfield_basket);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView2);

        BasketItem basketItem = (BasketItem) this.getItem(position);
        //imageView.setImageDrawable(basketItem.getImage());
        txtTitle.setText(basketItem.getTitle());
        editText.setText(""+basketItem.getCount());
        return rowView;
    }
}
