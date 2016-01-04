package de.hawhof.mc05.interDesign.myapplication2.app.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import de.hawhof.mc05.interDesign.myapplication2.app.MenueActivity;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.listViews.MenueExpandableAdapter;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Detail;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Menue;
import de.hawhof.mc05.interDesign.myapplication2.app.model.SubMenue;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 10.12.15.
 */
public class StartFragment2 extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ExpandableListView listView;
    private List<Menue> list;
    private int selectItem = 0;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StartFragment2 newInstance(int index) {

        StartFragment2 fragment = new StartFragment2();
        fragment.setselectItem(index);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);
        return fragment;
    }
    public StartFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menue2, container, false);
        this.listView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        Menue[] array = {new Menue(this.getResources().obtainTypedArray(R.array.Rezept),this.getContext()),new Menue(this.getResources().obtainTypedArray(R.array.KochBox),this.getContext()),new Menue(this.getResources().obtainTypedArray(R.array.Einzelprodukte),this.getContext())};
        this.list = Arrays.asList(array);
        MenueExpandableAdapter adapter = new MenueExpandableAdapter<Menue>(this.getContext(),list);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetInvalidated();
        listView.setAdapter(adapter);
        listView.invalidate();

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                list.get(groupPosition).getSubMenues().get(childPosition).getString(1);
                selectItem(new SubMenue(list.get(groupPosition).getSubMenues().get(childPosition),getActivity()));
                return false;
            }
        });

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if(selectItem != 0)
                    listView.collapseGroup(selectItem);
                selectItem = groupPosition;
                listView.setSelectedGroup(groupPosition);
               // listView.smoothScrollToPosition(groupPosition);
                listView.smoothScrollBy(300,1000);
                ((MenueActivity) getActivity()).setToolBarTitle(list.get(groupPosition).getTitle());
            }
        });

        if(this.selectItem != 0)
            listView.expandGroup(this.selectItem-1);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MenueActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void selectItem(SubMenue subMenue ) {

        Detail[] details = new Detail[subMenue.getSubMenues().get(0).length()];
        for(int z = 0;z<details.length;z++)
            details[z] = new Detail(this.getResources().obtainTypedArray(subMenue.getSubMenues().get(z).getResourceId(z,-8)));
        ((MenueActivity) this.getActivity()).setSubMenue(subMenue);
        ((MenueActivity) this.getActivity()).setDetails(details);
        ((MenueActivity) this.getActivity()).onNavigationDrawerItemSelected(9);
        ((MenueActivity) this.getActivity()).setMyTitle(subMenue.getTitle());
    }

    public void setselectItem(int selectItem) {
        this.selectItem = selectItem;
    }
}
