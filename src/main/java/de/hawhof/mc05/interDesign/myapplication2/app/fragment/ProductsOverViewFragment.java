package de.hawhof.mc05.interDesign.myapplication2.app.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import de.hawhof.mc05.interDesign.myapplication2.app.MenueActivity;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.listViews.ProductsOverView_ArrayAdapter;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Detail;

import java.util.Arrays;

/**
 * Created by alex on 03.12.15.
 */
public class ProductsOverViewFragment extends Fragment {
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
    public static Fragment newInstance(int index) {

        ProductsOverViewFragment fragment = new ProductsOverViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductsOverViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menue, container, false);
        this.listView = (ListView) rootView.findViewById(R.id.listView_menue);

        SharedPreferences sharedPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);

        ProductsOverView_ArrayAdapter adapter = new ProductsOverView_ArrayAdapter<Detail>(this.getContext(), R.layout.menue2, Arrays.asList(((MenueActivity) this.getActivity()).getDetails()));
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

    private void selectItem(int position) {
        if (listView != null) {
            listView.setItemChecked(position, true);
        }
        ((MenueActivity) this.getActivity()).setDetail((Detail) this.listView.getAdapter().getItem(position));
        ((MenueActivity) this.getActivity()).onNavigationDrawerItemSelected(12);
    }
}
