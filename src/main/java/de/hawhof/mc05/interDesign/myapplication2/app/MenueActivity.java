package de.hawhof.mc05.interDesign.myapplication2.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.*;
import com.google.gson.Gson;
import de.hawhof.mc05.interDesign.myapplication2.app.Controller.StorageController;
import de.hawhof.mc05.interDesign.myapplication2.app.fragment.*;
import de.hawhof.mc05.interDesign.myapplication2.app.model.Detail;
import de.hawhof.mc05.interDesign.myapplication2.app.model.ShoppingBasket;

import java.io.IOException;

public class MenueActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private final static int START = 0;
    private final static int REZEPT = 1;
    private final static int REZEPT_1 = 11;
    private final static int REZEPT_2 = 12;
    private final static int REZEPT_3 = 13;
    private final static int KOCHBOX = 2;
    private final static int KOCHBOX_1 = 21;
    private final static int KOCHBOX_2 = 22;
    private final static int KOCHBOX_3 = 23;
    private final static int EINZEL = 3;
    private final static int EINZEL_1 = 31;
    private final static int EINZEL_2 = 32;
    private final static int EINZEL_3 = 33;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Detail[] details;
    private Detail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
        this.getResources().obtainTypedArray(R.array.Start).getInt(0,-1);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        try {
            StorageController.file.mkdirs();
            StorageController.file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        getSupportFragmentManager().beginTransaction().replace(R.id.container, StartFragment2.newInstance(MenueActivity.START)).commit();
       // getSupportFragmentManager().beginTransaction().replace(R.id.container, StartFragment.newInstance(MenueActivity.START)).commit();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        if(position == 0){
            fragment = StartFragment.newInstance(position + 1);
        }else if(position < 4){
            fragment = SubMenueFragment.newInstance(position+1);
        }else if(position < 9){
            fragment = ShoppingBasketFragment.newInstance(position+1);
        }else if(position == 9){
            fragment = ProductsOverViewFragment.newInstance(position+1);
        }else{
            fragment = DetailFragment.newInstance(position+1);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment ).addToBackStack(null).commit();
    }

    public void onSectionAttached(int number) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section1);
                editor.putString("PAGE",getString(R.string.title_section1));
                break;
            case 1:
                mTitle = getString(R.string.title_section1);
                editor.putString("PAGE",getString(R.string.title_section1));
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                editor.putInt("TYPE",3);
                editor.putString("PAGE",getString(R.string.title_section2));
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                editor.putInt("TYPE",4);
                editor.putString("PAGE",getString(R.string.title_section3));
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                editor.putInt("TYPE",5);
                editor.putString("PAGE",getString(R.string.title_section4));
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                editor.putString("PAGE",getString(R.string.title_section5));
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                editor.putString("PAGE",getString(R.string.title_section6));
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                editor.putString("PAGE",getString(R.string.title_section7));
                break;
            case 8:
                mTitle = getString(R.string.title_section8);
                editor.putString("PAGE",getString(R.string.title_section8));
                break;
            case 9:
                mTitle = getString(R.string.title_section8);
                editor.putString("PAGE",getString(R.string.title_section8));
                break;
            case 10:
                mTitle = this.detail.getTitle();
                editor.putString("PAGE","Produkt");
                break;
            //Ab hier Einzelprodukte siehe id der Einzelprodukte
            default:

        }
        this.getSupportActionBar().setTitle(this.mTitle);
        this.setTitle(this.mTitle);
        editor.commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menue, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.action_settings)
            this.onNavigationDrawerItemSelected(4);
        //noinspection SimplifiableIfStatement
        /*f (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public void setDetails(Detail[] details) {
        this.details = details;
    }

    public Detail[] getDetails() {
        return details;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Detail getDetail() {
        return detail;
    }

    @Override
    public void onBackPressed(){
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            this.finish();
        }else{
            getSupportFragmentManager().popBackStack();
        }
    }
}
