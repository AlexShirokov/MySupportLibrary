package com.doublebrain.mysupportlibrary.views.main;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.doublebrain.mysupportlibrary.R;
import com.doublebrain.mysupportlibrary.views.fragments.FragmentListViewSample;
import com.doublebrain.mysupportlibrary.views.fragments.Fragment2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexShredder on 23.06.2016.
 */
public class MainController {

    private static AppCompatActivity mActivity;
    private static MainController instance;
    private FragmentListViewSample fragmentListViewSample;
    private Fragment2 frag2;
    private int currentFragment;
    private final int LISTVIEW = 0;
    private final int FRAGMENT2 = 1;


    private MainController(){

    }

    static MainController init(AppCompatActivity activity){
        if (instance==null) instance = new MainController();
        instance.restartActivity(activity);
        return instance;
    }

    private void restartActivity(AppCompatActivity activity) {
        mActivity = activity;

        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.toolbar_actionbar);

        mActivity.setSupportActionBar(toolbar);
        mActivity.setTitle("");
        // Spinner element
        Spinner spinner = (Spinner) mActivity.findViewById(R.id.spinner);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("ListView sample");
        categories.add("Frag2");
        // Creating adapter for spinner
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mActivity, R.layout.spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switchToFragment(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        switchToFragment(currentFragment);

    }


    private void switchToFragment(int fragment){
        Fragment cur=null;
        switch (fragment){
            case LISTVIEW:
                if (fragmentListViewSample == null) fragmentListViewSample = new FragmentListViewSample();
                cur = fragmentListViewSample;
                break;
            case FRAGMENT2:
                if (frag2==null) frag2 = new Fragment2();
                cur = frag2;
                break;

        }

        android.support.v4.app.FragmentTransaction fTrans = mActivity.getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.frameLayout, cur);
        fTrans.commit();
        currentFragment = fragment;
    }
}
