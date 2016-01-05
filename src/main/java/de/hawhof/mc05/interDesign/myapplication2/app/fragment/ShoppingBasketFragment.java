package de.hawhof.mc05.interDesign.myapplication2.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import de.hawhof.mc05.interDesign.myapplication2.app.MenueActivity;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.listViews.ShoppingBasket_ArrayAdapter;
import de.hawhof.mc05.interDesign.myapplication2.app.model.BasketItem;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Detail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 03.12.15.
 */
public class ShoppingBasketFragment  extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ListView listView;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ShoppingBasketFragment newInstance(int index) {

        ShoppingBasketFragment fragment = new ShoppingBasketFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);
        return fragment;
    }
    public ShoppingBasketFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basket, container, false);
        this.listView = (ListView) rootView.findViewById(R.id.listView_basket);
        this.listView.setAdapter(new ShoppingBasket_ArrayAdapter<BasketItem>(this.getContext(),0,this.getBasket()));
        Button backBut = (Button) rootView.findViewById(R.id.back_button);
        backBut.setOnClickListener( new View.OnClickListener(){

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        final TextView erg = (TextView) rootView.findViewById(R.id.erg);
        erg.setText(this.createErg(this.getBasket()));
        return rootView;
    }

    private CharSequence createErg(final List<BasketItem> basket) {
        double erg = 0.0;
        for(BasketItem item : basket)
            erg += item.getCount()*item.getPrice();
        return "Rechnungsbetrag: "+erg+"€";
    }

    private List<BasketItem> getBasket() {
        final List<BasketItem> list = new ArrayList<BasketItem>();
        list.add(new BasketItem(new Detail(this.getResources().obtainTypedArray(R.array.Einzelpodukt1)),3));list.add(new BasketItem(new Detail(this.getResources().obtainTypedArray(R.array.Einzelpodukt2)),3));
        list.add(new BasketItem(new Detail(this.getResources().obtainTypedArray(R.array.Einzelpodukt3)),5));list.add(new BasketItem(new Detail(this.getResources().obtainTypedArray(R.array.Einzelpodukt4)),2));
        list.add(new BasketItem(new Detail(this.getResources().obtainTypedArray(R.array.Einzelpodukt5)),1));
        return list;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MenueActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void selectItem(int position) {
        if (listView != null) {
            listView.setItemChecked(position, true);
        }

        ((MenueActivity)this.getActivity()).onNavigationDrawerItemSelected(position+1);
    }
}

