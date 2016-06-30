package com.doublebrain.mysupportlibrary.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.doublebrain.mysupportlibrary.R;
import com.doublebrain.mysupportlibrary.models.ListItem;
import com.doublebrain.mysupportlibrary.models.RecyclerViewSimplifiedSample;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerViewSample extends Fragment {

    private static final String TAG = "RecyclerView fragment 1";
    private List<ListItem> list;
    private RecyclerViewSimplifiedSample listView;

    public FragmentRecyclerViewSample() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        list = new ArrayList<>();
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_recycler_sample, container, false);

        //init listview
        listView = (RecyclerViewSimplifiedSample) rootView.findViewById(R.id.RecyclerView);
        listView.init(R.layout.list_item,list);

        Button button = (Button) rootView.findViewById(R.id.button_frag1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(new ListItem("String "+String.valueOf(list.size()+1),"2nd String "+String.valueOf(list.size()+1)));
                listView.notifyDataSetChanged();
            }
        });

        return rootView;
    }

}
