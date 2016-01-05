package de.hawhof.mc05.interDesign.myapplication2.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import de.hawhof.mc05.interDesign.myapplication2.app.MenueActivity;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Detail;

/**
 * Created by alex on 03.12.15.
 */
public class DetailFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Detail detail;
    private FloatingActionButton imageButton;
    private boolean isSelect;
    private EditText editText;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DetailFragment newInstance(int index) {

        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scrolling, container, false);
        this.detail = ((MenueActivity) this.getActivity()).getDetail();

        final TextView describeView = (TextView) rootView.findViewById(R.id.describe_view);
        final TextView priceView = (TextView) rootView.findViewById(R.id.priceView);
        final ImageView titleImageView = (ImageView) rootView.findViewById(R.id.imageView);
        this.editText = (EditText) rootView.findViewById(R.id.countProduct);
        this.imageButton = (FloatingActionButton) rootView.findViewById(R.id.fab1);

        this.isSelect = false;
        if (isSelect)
            imageButton.setImageResource(android.R.drawable.btn_star_big_on);
        else
            imageButton.setImageResource(android.R.drawable.btn_star_big_off);
        ((MenueActivity) this.getActivity()).getSupportActionBar().setTitle(this.detail.getTitle());
        titleImageView.setImageDrawable(this.detail.getImage());
        describeView.setText(this.detail.getDes());
        describeView.setMovementMethod(new ScrollingMovementMethod());
        priceView.setText(this.detail.getPriceString() +" pro "+this.detail.getType());


        this.imageButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if (!isSelect)
                    imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                else
                    imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                isSelect = !isSelect;
            }
        });

        ((FloatingActionButton) rootView.findViewById(R.id.fab2)).setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Es wurden " + editText.getText() + " " + detail.getType() + " " + detail.getTitle() + " hinzugefügt", Toast.LENGTH_LONG).show();
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
}

