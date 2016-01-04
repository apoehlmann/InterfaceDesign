package de.hawhof.mc05.interDesign.myapplication2.app.listViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.hawhof.mc05.interDesign.myapplication2.app.R;

import java.util.List;

/**
 * Created by alex on 24.11.15.
 */
public class Sidebar_ArrayAdapter<T> extends ArrayAdapter<T> {

    private final Context context;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public Sidebar_ArrayAdapter(Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View rowView= inflater.inflate(R.layout.sidebar, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textsidebar);
        txtTitle.setText((CharSequence) this.getItem(position));

        TextView textView = (TextView) rowView.findViewById(R.id.baskettextfield_basket);
        return rowView;
    }
}
