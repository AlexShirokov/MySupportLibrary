package com.doublebrain.mysupportlibrary.models;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.doublebrain.mysupportlibrary.abstracts.ListViewSimplified;

import java.util.List;

/**
 * Created by AlexShredder on 29.06.2016.
 */
public class ListViewSimplifiedSample extends ListViewSimplified {

    public ListViewSimplifiedSample(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListViewSimplifiedSample(Context context) {
        super(context);
    }

    public ListViewSimplifiedSample(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void processView(View row, TextView textView1, TextView textView2, int position) {
        List list = getItemsList();
        ListItem listItem = (ListItem) list.get(position);
        textView1.setText(listItem.text1);
        textView2.setText(listItem.text2);
    }
}
