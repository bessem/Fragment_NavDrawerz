package pirana.aka.fragment_navdrawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import pirana.aka.fragment_navdrawer.fragment.FirstFragment;
import pirana.aka.fragment_navdrawer.fragment.SecondFragment;
import pirana.aka.fragment_navdrawer.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener
        ,SecondFragment.OnFragmentInteractionListener
        ,ThirdFragment.OnFragmentInteractionListener{
    final Uri imageUri = Uri.parse("https://i.imgur.com/tGbaZCY.jpg");
    private Class fragmentClass;
    private Fragment fragment = null;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private String fragmentTitle;
    private int drawerItemPostion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize fresco before setting context is mendatory
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        SimpleDraweeView draweeView = (SimpleDraweeView) headerView.findViewById(R.id.fresco_image_view);
        draweeView.setImageURI(imageUri);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //replace actionBar with toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                switch (drawerItemPostion){
                    case R.id.nav_first_fragment:
                        fragmentClass = FirstFragment.class;
                        break;
                    case R.id.nav_second_fragment:
                        fragmentClass = SecondFragment.class;
                        fragmentTitle = "List View";
                        break;
                    case R.id.nav_third_fragment:
                        fragmentClass = ThirdFragment.class;
                        fragmentTitle = "Recycle/Card View ";
                        break;
                    default:
                        fragmentClass = FirstFragment.class;
                        fragmentTitle = "First Fragment";
                }
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent,fragment)
                        .commit();
                setTitle(fragmentTitle);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        drawerToggle = new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.drawer_open,R.string.drawer_close){
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                switch (drawerItemPostion){
                    case R.id.nav_first_fragment:
                        fragmentClass = FirstFragment.class;
                        break;
                    case R.id.nav_second_fragment:
                        fragmentClass = SecondFragment.class;
                        fragmentTitle = "List View";
                        break;
                    case R.id.nav_third_fragment:
                        fragmentClass = ThirdFragment.class;
                        fragmentTitle = "Recycle/Card View ";
                        break;
                    default:
                        fragmentClass = FirstFragment.class;
                        fragmentTitle = "First Fragment";
                }
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent,fragment)
                        .commit();
                setTitle(fragmentTitle);
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }};
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

        @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    private void setupDrawerContent(NavigationView navigationView ){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        selectDrawerItem(item);
                        return true;
                    }
                }
        );
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThirdFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }
        drawerItemPostion = item.getItemId();
        mDrawer.closeDrawers();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                mDrawer.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //Ask the user if they want to quit
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.quit)
                    .setMessage(R.string.really_quit)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Stop the activity
                            MainActivity.this.finish();
                        }

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
