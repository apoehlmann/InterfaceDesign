package de.hawhof.mc05.interDesign.myapplication2.app.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import de.hawhof.mc05.interDesign.myapplication2.app.Controller.StorageController;
import de.hawhof.mc05.interDesign.myapplication2.app.MenueActivity;
import de.hawhof.mc05.interDesign.myapplication2.app.R;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Detail;
import de.hawhof.mc05.interDesign.myapplication2.app.model.ShoppingBasket;

import java.io.*;

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
    private ImageButton imageButton;
    private boolean isSelect;
    private EditText editText;
    private ShoppingBasket shoppingBasket = null;

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
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        this.detail = ((MenueActivity) this.getActivity()).getDetail();


        final TextView titleView = (TextView) rootView.findViewById(R.id.detail_title);
        final TextView describeView = (TextView) rootView.findViewById(R.id.describe_view);
        final TextView priceView = (TextView) rootView.findViewById(R.id.priceView);
        this.editText = (EditText) rootView.findViewById(R.id.countProduct);
        this.imageButton = (ImageButton) rootView.findViewById(R.id.favorite);
        try {
            FileInputStream fos = new FileInputStream(StorageController.file);//this.getActivity().openFileInput(StorageController.file);
            ObjectInputStream ois = new ObjectInputStream(fos);
            this.shoppingBasket = (ShoppingBasket) ois.readObject();
            ois.close();

            if(shoppingBasket != null){
                if(shoppingBasket.findItem(this.detail) != null){
                    this.isSelect = shoppingBasket.findItem(this.detail).getIsFav();
                    if(isSelect)
                        imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                    else
                        imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                    this.editText.setText(""+shoppingBasket.findItem(this.detail).getCount());
                }else{
                    this.isSelect = false;
                    if(isSelect)
                        imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                    else
                        imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                    this.editText.setText("0");
                }
            }else{
                this.isSelect = false;
                if(!isSelect)
                    imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                else
                    imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                this.editText.setText("0");
            }
        } catch (Exception e){
            e.printStackTrace();
            this.shoppingBasket = new ShoppingBasket();
        }

        titleView.setText(this.detail.getTitle());
        describeView.setText(this.detail.getDes());
        describeView.setMovementMethod(new ScrollingMovementMethod());
        priceView.setText(this.detail.getPriceString());


        this.imageButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if(!isSelect)
                    imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                else
                    imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                isSelect = !isSelect;
            }
        });

        ((ImageButton)rootView.findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Es wurden "+editText.getText()+" "+detail.getType()+" "+detail.getTitle()+" hinzugefügt",Toast.LENGTH_LONG).show();
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

    @Override
    public void onPause(){

        try {
            FileOutputStream fos = new FileOutputStream(StorageController.file);//this.getActivity().openFileOutput(StorageController.file,Context.MODE_APPEND);
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            this.shoppingBasket.updateItem(this.detail,new Integer(String.valueOf(this.editText.getText())));
            this.shoppingBasket.setFav(this.detail,this.isSelect);
            ois.writeObject(this.shoppingBasket);
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onPause();
    }
}

