package de.hawhof.mc05.interDesign.myapplication2.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import de.hawhof.mc05.interDesign.myapplication2.app.Controller.StorageController;
import de.hawhof.mc05.interDesign.myapplication2.app.MenueActivity;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.listViews.Menue_ArrayAdapter;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Menue;
import de.hawhof.mc05.interDesign.myapplication2.app.model.ShoppingBasket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
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
        this.listView = (ListView) rootView.findViewById(R.id.listView_menue);

        try {
            FileInputStream fos = new FileInputStream(StorageController.file);
            ObjectInputStream ois = new ObjectInputStream(fos);
            final ShoppingBasket shoppingBasket = (ShoppingBasket) ois.readObject();
            if(shoppingBasket != null){
               // shoppingBasket.getAllItems();
            }else{

            }
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*Menue[] array = {new Menue(this.getResources().obtainTypedArray(R.array.Rezept),this.getContext()),new Menue(this.getResources().obtainTypedArray(R.array.KochBox),this.getContext()),new Menue(this.getResources().obtainTypedArray(R.array.Einzelprodukte),this.getContext())};
        List<Menue> list = Arrays.asList(array);
        Menue_ArrayAdapter adapter = new Menue_ArrayAdapter<Menue>(this.getContext(),R.layout.menue,list);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetInvalidated();
        listView.setAdapter(adapter);*/
        //listView.invalidate();
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });*/
        //add
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
        return rootView;
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

