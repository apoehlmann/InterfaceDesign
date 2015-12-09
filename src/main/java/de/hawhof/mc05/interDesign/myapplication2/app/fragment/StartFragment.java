package de.hawhof.mc05.interDesign.myapplication2.app.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.gson.Gson;
import de.hawhof.mc05.interDesign.myapplication2.app.MenueActivity;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.listViews.Menue_ArrayAdapter;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Menue;
import de.hawhof.mc05.interDesign.myapplication2.app.model.ShoppingBasket;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 03.12.15.
 */
public class StartFragment extends Fragment {
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
    public static StartFragment newInstance(int index) {

        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);
        return fragment;
    }
    public StartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menue, container, false);
        this.listView = (ListView) rootView.findViewById(R.id.listView_menue);
        Menue[] array = {new Menue(this.getResources().obtainTypedArray(R.array.Rezept),this.getContext()),new Menue(this.getResources().obtainTypedArray(R.array.KochBox),this.getContext()),new Menue(this.getResources().obtainTypedArray(R.array.Einzelprodukte),this.getContext())};
        List<Menue> list = Arrays.asList(array);
        Menue_ArrayAdapter adapter = new Menue_ArrayAdapter<Menue>(this.getContext(),R.layout.menue,list);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetInvalidated();
        listView.setAdapter(adapter);
        listView.invalidate();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        //add


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
