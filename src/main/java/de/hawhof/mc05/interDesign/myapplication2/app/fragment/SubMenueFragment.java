package de.hawhof.mc05.interDesign.myapplication2.app.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import de.hawhof.mc05.interDesign.myapplication2.app.MenueActivity;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.listViews.SubMenue_ArrayAdapter;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Detail;
import de.hawhof.mc05.interDesign.myapplication2.app.model.SubMenue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 03.12.15.
 */
public class SubMenueFragment extends Fragment {
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
    public static SubMenueFragment newInstance(int index) {

        SubMenueFragment fragment = new SubMenueFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);
        return fragment;
    }

    public SubMenueFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menue, container, false);
        this.listView = (ListView) rootView.findViewById(R.id.listView_menue);

        SharedPreferences sharedPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);

        List<SubMenue> list = getMenueItems(sharedPref.getString("PAGE",""));
        SubMenue_ArrayAdapter adapter = new SubMenue_ArrayAdapter<SubMenue>(this.getContext(), R.layout.menue, list);
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

    private  List<SubMenue> getMenueItems(String page) {
        List<SubMenue> list = new ArrayList<SubMenue>();
        TypedArray array = null;
        if (page.length() == "Rezepte".length()) {
            array = this.getResources().obtainTypedArray(R.array.Rezept);
        }else if (page.length() == "Wochenpakete".length()){
                array = this.getResources().obtainTypedArray(R.array.KochBox);
        }else if (page.length() =="Einzelprodukte".length()){
                array = this.getResources().obtainTypedArray(R.array.Einzelprodukte);
        }
        for(int z = 3; z<array.length();z++)
              list.add(new SubMenue(this.getResources().obtainTypedArray(array.getResourceId(z,3)),this.getActivity()));
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
        Detail[] details = new Detail[((SubMenue) listView.getAdapter().getItem(position)).getSubMenues().get(0).length()];
        for(int z = 0;z<details.length;z++)
            details[z] = new Detail(this.getResources().obtainTypedArray(((SubMenue) listView.getAdapter().getItem(position)).getSubMenues().get(z).getResourceId(z,0)));
        ((MenueActivity) this.getActivity()).setDetails(details);
        ((MenueActivity) this.getActivity()).onNavigationDrawerItemSelected(9);
    }
}
