package com.doublebrain.mysupportlibrary.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.doublebrain.mysupportlibrary.R;
import com.doublebrain.mysupportlibrary.abstracts.ListViewSimplified;
import com.doublebrain.mysupportlibrary.models.ListItem;
import com.doublebrain.mysupportlibrary.models.ListViewSimplifiedSample;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListViewSample extends Fragment {


    private static final String TAG = "ListView fragment 1";
    private ListViewSimplifiedSample.LVList<ListItem> list;
    private ListViewSimplifiedSample listView;

    public FragmentListViewSample() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"onCreate");
        list = new ListViewSimplified.LVList<>();
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG,"onCreateView");

        View rootView = inflater.inflate(R.layout.fragment_list_view_sample, container, false);

        //init listview
        listView = (ListViewSimplifiedSample) rootView.findViewById(R.id.listView_frag1);
        listView.initAdapter(R.layout.list_item,R.id.listItemText1,R.id.listItemText2,list);

        Button button = (Button) rootView.findViewById(R.id.button_frag1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(new ListItem("String "+String.valueOf(list.size()+1),"2nd String "+String.valueOf(list.size()+1)));
            }
        });
        return rootView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView");
    }
}
